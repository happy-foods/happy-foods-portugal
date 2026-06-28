package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.GeminiService
import com.example.data.AppDatabase
import com.example.data.ChatMessage
import com.example.data.CatalogMeal
import com.example.data.FoodCatalog
import com.example.data.MealRepository
import com.example.data.SavedMeal
import com.example.data.SubscriptionPlan
import com.example.data.UserPreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppTab {
    HOME,
    EXPLORE,
    AI_CHEF,
    PROFILE
}

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MealRepository

    // --- Database-backed Flows ---
    val userPreference: StateFlow<UserPreference?>
    val subscription: StateFlow<SubscriptionPlan?>
    val savedMeals: StateFlow<List<SavedMeal>>
    val chatHistory: StateFlow<List<ChatMessage>>

    // --- UI Local States ---
    private val _currentTab = MutableStateFlow(AppTab.HOME)
    val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedGoalFilter = MutableStateFlow("All")
    val selectedGoalFilter: StateFlow<String> = _selectedGoalFilter.asStateFlow()

    private val _selectedDietFilter = MutableStateFlow("All")
    val selectedDietFilter: StateFlow<String> = _selectedDietFilter.asStateFlow()

    private val _isAiLoading = MutableStateFlow(false)
    val isAiLoading: StateFlow<Boolean> = _isAiLoading.asStateFlow()

    // Calories tracker state (resets on app open or custom action)
    private val _consumedCalories = MutableStateFlow(1250) // Default baseline progress
    val consumedCalories: StateFlow<Int> = _consumedCalories.asStateFlow()

    private val _consumedProtein = MutableStateFlow(75)
    val consumedProtein: StateFlow<Int> = _consumedProtein.asStateFlow()

    private val _consumedCarbs = MutableStateFlow(140)
    val consumedCarbs: StateFlow<Int> = _consumedCarbs.asStateFlow()

    private val _consumedFats = MutableStateFlow(45)
    val consumedFats: StateFlow<Int> = _consumedFats.asStateFlow()

    init {
        val database = AppDatabase.getDatabase(application)
        repository = MealRepository(database)

        userPreference = repository.userPreference.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserPreference()
        )

        subscription = repository.subscription.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SubscriptionPlan()
        )

        savedMeals = repository.savedMeals.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        chatHistory = repository.chatHistory.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        // Prepopulate default configuration values if db is empty
        viewModelScope.launch {
            if (repository.getUserPref().name == "Healthy Eater" && database.userPreferenceDao().getUserPreference() == null) {
                repository.saveUserPref(UserPreference())
            }
            if (repository.getSub().planName == "Free Basic Plan" && database.subscriptionDao().getSubscription() == null) {
                repository.saveSubscription(SubscriptionPlan())
            }
            // Add initial friendly welcome chat from chef if empty
            if (database.chatDao().getChatHistoryFlow().stateIn(viewModelScope).value.isEmpty()) {
                repository.addChatMessage(
                    ChatMessage(
                        message = "Welcome to Happy Foods Portugal! 🇵🇹 I'm your Happy Chef Nutritionist. I can generate personalized recipe adaptations, suggest ingredients based on what you have, or answer any diet questions! How can I make your day happier and healthier?",
                        isUser = false
                    )
                )
            }
        }
    }

    // --- Actions ---

    fun setTab(tab: AppTab) {
        _currentTab.value = tab
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setGoalFilter(goal: String) {
        _selectedGoalFilter.value = goal
    }

    fun setDietFilter(diet: String) {
        _selectedDietFilter.value = diet
    }

    fun updateProfile(name: String, goal: String, diet: String, calories: Int) {
        viewModelScope.launch {
            val current = repository.getUserPref()
            repository.saveUserPref(
                current.copy(
                    name = name,
                    goal = goal,
                    diet = diet,
                    dailyCalorieTarget = calories
                )
            )
        }
    }

    fun updateSubscription(planName: String, price: Double, address: String, days: String, time: String) {
        viewModelScope.launch {
            val current = repository.getSub()
            repository.saveSubscription(
                current.copy(
                    planName = planName,
                    price = price,
                    deliveryAddress = address,
                    deliveryDays = days,
                    deliveryTime = time,
                    status = "Active",
                    startDate = System.currentTimeMillis()
                )
            )
        }
    }

    fun cancelSubscription() {
        viewModelScope.launch {
            val current = repository.getSub()
            repository.saveSubscription(
                current.copy(
                    planName = "Free Basic Plan",
                    price = 0.0,
                    status = "Cancelled"
                )
            )
        }
    }

    fun toggleFavorite(meal: CatalogMeal) {
        viewModelScope.launch {
            if (repository.isMealSaved(meal.id)) {
                repository.removeMeal(meal.id)
            } else {
                repository.saveMeal(
                    SavedMeal(
                        id = meal.id,
                        title = meal.title,
                        category = meal.category,
                        calories = meal.calories,
                        protein = meal.protein,
                        carbs = meal.carbs,
                        fats = meal.fats,
                        imageUrl = meal.imageUrl,
                        ingredients = meal.ingredients.joinToString("\n"),
                        instructions = meal.instructions.joinToString("\n")
                    )
                )
            }
        }
    }

    fun logEatenMeal(meal: CatalogMeal) {
        _consumedCalories.value += meal.calories
        _consumedProtein.value += meal.protein
        _consumedCarbs.value += meal.carbs
        _consumedFats.value += meal.fats
    }

    fun resetDailyIntake() {
        _consumedCalories.value = 0
        _consumedProtein.value = 0
        _consumedCarbs.value = 0
        _consumedFats.value = 0
    }

    fun sendMessageToChef(message: String) {
        if (message.isBlank()) return
        
        viewModelScope.launch {
            // Add user message to local database history
            repository.addChatMessage(ChatMessage(message = message, isUser = true))
            _isAiLoading.value = true

            // Fetch user info for context
            val pref = repository.getUserPref()
            val history = databaseHistoryList()

            // Call Gemini API
            val response = GeminiService.generateChatResponse(history, message, pref)

            // Add chef response to database
            repository.addChatMessage(ChatMessage(message = response, isUser = false))
            _isAiLoading.value = false
        }
    }

    fun clearChatHistory() {
        viewModelScope.launch {
            repository.clearChat()
            repository.addChatMessage(
                ChatMessage(
                    message = "Chat history cleared! 🧹 What other tasty and nutritious secrets should we discuss today?",
                    isUser = false
                )
            )
        }
    }

    private suspend fun databaseHistoryList(): List<ChatMessage> {
        val db = AppDatabase.getDatabase(getApplication())
        // Wait, a simple query from dao is faster
        return db.chatDao().getChatHistoryFlow().stateIn(viewModelScope).value
    }
}
