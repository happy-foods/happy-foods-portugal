package com.example.api

import com.example.BuildConfig
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

@JsonClass(generateAdapter = true)
data class Part(
    @Json(name = "text") val text: String? = null
)

@JsonClass(generateAdapter = true)
data class Content(
    @Json(name = "parts") val parts: List<Part>
)

@JsonClass(generateAdapter = true)
data class GenerateContentRequest(
    @Json(name = "contents") val contents: List<Content>,
    @Json(name = "systemInstruction") val systemInstruction: Content? = null
)

@JsonClass(generateAdapter = true)
data class Candidate(
    @Json(name = "content") val content: Content?
)

@JsonClass(generateAdapter = true)
data class GenerateContentResponse(
    @Json(name = "candidates") val candidates: List<Candidate>?
)

interface GeminiApiService {
    @POST("v1beta/models/gemini-3.5-flash:generateContent")
    suspend fun generateContent(
        @Query("key") apiKey: String,
        @Body request: GenerateContentRequest
    ): GenerateContentResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://generativelanguage.googleapis.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    val service: GeminiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GeminiApiService::class.java)
    }
}

object GeminiService {
    suspend fun generateChatResponse(
        chatHistory: List<com.example.data.ChatMessage>,
        userMessage: String,
        userPreferences: com.example.data.UserPreference
    ): String {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return "Hi there! I'm the Happy Chef Assistant. (Note: Gemini API key is not configured in Secrets, but I can still recommend healthy, delicious alternatives! Feel free to ask me anything about our meal plans, or try configuring your API key in AI Studio to unlock live AI responses!)"
        }

        val systemPrompt = """
            You are "Happy Chef", the intelligent, friendly, and expert nutritionist/chef for the Happy Foods Portugal business.
            Your job is to assist the user with healthy meal prep, custom dietary suggestions, recipe swaps, and fitness goals.
            The user's profile preferences are:
            - Name: ${userPreferences.name}
            - Goal: ${userPreferences.goal}
            - Dietary Restriction: ${userPreferences.diet}
            - Daily Calorie Target: ${userPreferences.dailyCalorieTarget} kcal
            
            Keep your responses concise, cheerful, encouraging, and focused on helping them eat well and feel happy. Use healthy food emojis occasionally to bring joy!
            Avoid long-winded answers. Provide highly structured formatting with lists and bold terms where helpful.
        """.trimIndent()

        // Build Gemini conversation history representation
        val contents = mutableListOf<Content>()
        
        // Add previous history (limit to last 10 messages to save context limits)
        chatHistory.takeLast(10).forEach { msg ->
            contents.add(
                Content(parts = listOf(Part(text = msg.message)))
            )
        }
        
        // Add current user prompt
        contents.add(
            Content(parts = listOf(Part(text = userMessage)))
        )

        val requestBody = GenerateContentRequest(
            contents = contents,
            systemInstruction = Content(parts = listOf(Part(text = systemPrompt)))
        )

        return try {
            val response = RetrofitClient.service.generateContent(apiKey, requestBody)
            response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text 
                ?: "I couldn't quite think of a response right now. Let me know if you'd like to try another recipe idea!"
        } catch (e: Exception) {
            "Chef's offline: Let's focus on healthy eating! Here is a tip: Drinking a glass of lemon water first thing in the morning aids digestion and boosts energy levels. (Error: ${e.localizedMessage})"
        }
    }
}
