package com.example.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

// --- entities ---

@Entity(tableName = "user_preferences")
data class UserPreference(
    @PrimaryKey val id: Int = 1,
    val name: String = "Healthy Eater",
    val goal: String = "Healthy Living", // "Weight Loss", "Muscle Gain", "Healthy Living", "Balanced"
    val diet: String = "None", // "None", "Vegan", "Vegetarian", "Keto", "Gluten-Free", "Halal"
    val dailyCalorieTarget: Int = 2000
)

@Entity(tableName = "subscriptions")
data class SubscriptionPlan(
    @PrimaryKey val id: Int = 1,
    val planName: String = "Free Basic Plan", // "Free Basic Plan", "Happy Daily Plan", "Happy Family Feast"
    val price: Double = 0.0,
    val status: String = "Active", // "Active", "Cancelled", "None"
    val startDate: Long = System.currentTimeMillis(),
    val deliveryAddress: String = "123 Healthy Street, Garden City",
    val deliveryTime: String = "08:00 AM",
    val deliveryDays: String = "Monday, Wednesday, Friday" // Comma-separated
)

@Entity(tableName = "saved_meals")
data class SavedMeal(
    @PrimaryKey val id: String,
    val title: String,
    val category: String, // Breakfast, Lunch, Dinner, Snack
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fats: Int,
    val imageUrl: String,
    val ingredients: String, // Comma or newline-separated
    val instructions: String, // Newline-separated
    val savedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "chat_history")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val message: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

// --- DAOs ---

@Dao
interface UserPreferenceDao {
    @Query("SELECT * FROM user_preferences WHERE id = 1 LIMIT 1")
    fun getUserPreferenceFlow(): Flow<UserPreference?>

    @Query("SELECT * FROM user_preferences WHERE id = 1 LIMIT 1")
    suspend fun getUserPreference(): UserPreference?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserPreference(pref: UserPreference)
}

@Dao
interface SubscriptionDao {
    @Query("SELECT * FROM subscriptions WHERE id = 1 LIMIT 1")
    fun getSubscriptionFlow(): Flow<SubscriptionPlan?>

    @Query("SELECT * FROM subscriptions WHERE id = 1 LIMIT 1")
    suspend fun getSubscription(): SubscriptionPlan?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscription(sub: SubscriptionPlan)
}

@Dao
interface SavedMealDao {
    @Query("SELECT * FROM saved_meals ORDER BY savedAt DESC")
    fun getSavedMealsFlow(): Flow<List<SavedMeal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedMeal(meal: SavedMeal)

    @Query("DELETE FROM saved_meals WHERE id = :mealId")
    suspend fun deleteSavedMeal(mealId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM saved_meals WHERE id = :mealId LIMIT 1)")
    suspend fun isMealSaved(mealId: String): Boolean
}

@Dao
interface ChatDao {
    @Query("SELECT * FROM chat_history ORDER BY timestamp ASC")
    fun getChatHistoryFlow(): Flow<List<ChatMessage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(msg: ChatMessage)

    @Query("DELETE FROM chat_history")
    suspend fun clearChat()
}

// --- Database ---

@Database(
    entities = [UserPreference::class, SubscriptionPlan::class, SavedMeal::class, ChatMessage::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userPreferenceDao(): UserPreferenceDao
    abstract fun subscriptionDao(): SubscriptionDao
    abstract fun savedMealDao(): SavedMealDao
    abstract fun chatDao(): ChatDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "happy_foods_db"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// --- Repository ---

class MealRepository(private val db: AppDatabase) {
    val userPreference: Flow<UserPreference?> = db.userPreferenceDao().getUserPreferenceFlow()
    val subscription: Flow<SubscriptionPlan?> = db.subscriptionDao().getSubscriptionFlow()
    val savedMeals: Flow<List<SavedMeal>> = db.savedMealDao().getSavedMealsFlow()
    val chatHistory: Flow<List<ChatMessage>> = db.chatDao().getChatHistoryFlow()

    suspend fun getUserPref(): UserPreference {
        return db.userPreferenceDao().getUserPreference() ?: UserPreference()
    }

    suspend fun saveUserPref(pref: UserPreference) {
        db.userPreferenceDao().insertUserPreference(pref)
    }

    suspend fun getSub(): SubscriptionPlan {
        return db.subscriptionDao().getSubscription() ?: SubscriptionPlan()
    }

    suspend fun saveSubscription(sub: SubscriptionPlan) {
        db.subscriptionDao().insertSubscription(sub)
    }

    suspend fun isMealSaved(mealId: String): Boolean {
        return db.savedMealDao().isMealSaved(mealId)
    }

    suspend fun saveMeal(meal: SavedMeal) {
        db.savedMealDao().insertSavedMeal(meal)
    }

    suspend fun removeMeal(mealId: String) {
        db.savedMealDao().deleteSavedMeal(mealId)
    }

    suspend fun addChatMessage(msg: ChatMessage) {
        db.chatDao().insertMessage(msg)
    }

    suspend fun clearChat() {
        db.chatDao().clearChat()
    }
}
