package com.example.ui

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.R

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.BusinessPlanInfo
import com.example.data.CatalogMeal
import com.example.data.ChatMessage
import com.example.data.FoodCatalog
import com.example.data.SavedMeal
import com.example.data.SubscriptionPlan
import com.example.data.UserPreference

fun getMealImageUrl(id: String): String {
    return when (id) {
        "acai_bowl" -> "https://images.unsplash.com/photo-1590301157890-4810ed352733?auto=format&fit=crop&q=80&w=400"
        "avo_sourdough" -> "https://images.unsplash.com/photo-1541532713592-79a0317b6b77?auto=format&fit=crop&q=80&w=400"
        "egg_scramble" -> "https://images.unsplash.com/photo-1525351484163-7529414344d8?auto=format&fit=crop&q=80&w=400"
        "quinoa_salad" -> "https://images.unsplash.com/photo-1505576399279-565b52d4ac71?auto=format&fit=crop&q=80&w=400"
        "citrus_salmon" -> "https://images.unsplash.com/photo-1467003909585-2f8a72700288?auto=format&fit=crop&q=80&w=400"
        "keto_butter_chicken" -> "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?auto=format&fit=crop&q=80&w=400"
        "tofu_bowl" -> "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?auto=format&fit=crop&q=80&w=400"
        "lean_steak" -> "https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&q=80&w=400"
        "ginger_stirfry" -> "https://images.unsplash.com/photo-1512058564366-18510be2db19?auto=format&fit=crop&q=80&w=400"
        "greek_yogurt" -> "https://images.unsplash.com/photo-1488477181946-6428a0291777?auto=format&fit=crop&q=80&w=400"
        "almond_goji" -> "https://images.unsplash.com/photo-1596547609652-9cf5d8d76921?auto=format&fit=crop&q=80&w=400"
        else -> "https://images.unsplash.com/photo-1490645935967-10de6ba17061?auto=format&fit=crop&q=80&w=400"
    }
}

@Composable
fun MainAppScreen(viewModel: MainViewModel) {
    val currentTab by viewModel.currentTab.collectAsState()
    val userPref by viewModel.userPreference.collectAsState()
    val subPlan by viewModel.subscription.collectAsState()

    val safePref = userPref ?: UserPreference()
    val safeSub = subPlan ?: SubscriptionPlan()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF9F9F7) // Warm organic background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                when (currentTab) {
                    AppTab.HOME -> HomeScreen(viewModel, safePref, safeSub)
                    AppTab.EXPLORE -> ExploreScreen(viewModel, safePref)
                    AppTab.AI_CHEF -> ChefScreen(viewModel, safePref)
                    AppTab.PROFILE -> ProfileScreen(viewModel, safePref, safeSub)
                }
            }

            // Bottom Navigation
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationBarItem(
                    selected = currentTab == AppTab.HOME,
                    onClick = { viewModel.setTab(AppTab.HOME) },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Daily Plan", fontWeight = FontWeight.SemiBold, fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFB71C1C),
                        selectedTextColor = Color(0xFFB71C1C),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color(0xFFFFEBEE)
                    ),
                    modifier = Modifier.testTag("nav_home_tab")
                )
                NavigationBarItem(
                    selected = currentTab == AppTab.EXPLORE,
                    onClick = { viewModel.setTab(AppTab.EXPLORE) },
                    icon = { Icon(Icons.Default.Restaurant, contentDescription = "Menu") },
                    label = { Text("Explore Menu", fontWeight = FontWeight.SemiBold, fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFB71C1C),
                        selectedTextColor = Color(0xFFB71C1C),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color(0xFFFFEBEE)
                    ),
                    modifier = Modifier.testTag("nav_explore_tab")
                )
                NavigationBarItem(
                    selected = currentTab == AppTab.AI_CHEF,
                    onClick = { viewModel.setTab(AppTab.AI_CHEF) },
                    icon = { Icon(Icons.Default.SmartToy, contentDescription = "AI Chef Help") },
                    label = { Text("Chef Assistant", fontWeight = FontWeight.SemiBold, fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFB71C1C),
                        selectedTextColor = Color(0xFFB71C1C),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color(0xFFFFEBEE)
                    ),
                    modifier = Modifier.testTag("nav_ai_chef_tab")
                )
                NavigationBarItem(
                    selected = currentTab == AppTab.PROFILE,
                    onClick = { viewModel.setTab(AppTab.PROFILE) },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Subscription", fontWeight = FontWeight.SemiBold, fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFB71C1C),
                        selectedTextColor = Color(0xFFB71C1C),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color(0xFFFFEBEE)
                    ),
                    modifier = Modifier.testTag("nav_profile_tab")
                )
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel, pref: UserPreference, sub: SubscriptionPlan) {
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    var selectedDay by remember { mutableStateOf("Monday") }
    var previewPlanName by remember { mutableStateOf(if (pref.diet == "Halal" || pref.diet == "Non-Veg") "Non-Veg (Halal) Plan" else "Vegetarian Plan") }

    // Swapped meals simulation map
    val swappedMeals = remember { mutableStateMapOf<String, CatalogMeal>() }
    // Paused days simulation map
    val pausedDays = remember { mutableStateMapOf<String, Boolean>() }
    // Eaten/Marked meals map
    val markedMeals = remember { mutableStateMapOf<String, Boolean>() }

    var showSwapDialogForCategory by remember { mutableStateOf<String?>(null) } // "Breakfast", etc.
    var showSwapDialogDay by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // App Premium Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.img_logo_1782662261235),
                    contentDescription = "Happy Foods Portugal Logo",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(BorderStroke(1.dp, Color(0xFF2E7D32).copy(alpha = 0.2f)), RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Happy Foods Portugal 🇵🇹",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF2E7D32)
                    )
                    Text(
                        text = "Hi, ${pref.name}! Your gourmet subscription is active.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray
                    )
                }
            }
            IconButton(
                onClick = { /* Notification action */ },
                modifier = Modifier.background(Color(0xFFE8F5E9), CircleShape)
            ) {
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color(0xFF2E7D32)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Subscription Delivery Tracker (Cooking -> Out for Delivery -> Delivered)
        val isSubscriptionActive = sub.planName != "Free Basic Plan" && sub.status == "Active"
        val isPausedThisDay = pausedDays[selectedDay] ?: false

        if (isSubscriptionActive) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.DeliveryDining,
                                contentDescription = "Delivery",
                                tint = if (isPausedThisDay) Color.Gray else Color(0xFF2E7D32),
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isPausedThisDay) "$selectedDay Box Paused" else "Today's Delivery Plan",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (isPausedThisDay) Color.Gray else Color(0xFF2E7D32)
                            )
                        }
                        // Pause Toggle Button
                        Button(
                            onClick = { pausedDays[selectedDay] = !isPausedThisDay },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isPausedThisDay) Color(0xFFE8F5E9) else Color(0xFFFFEBEE),
                                contentColor = if (isPausedThisDay) Color(0xFF2E7D32) else Color(0xFFD32F2F)
                            ),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = if (isPausedThisDay) "Resume Box" else "Pause Box",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    if (!isPausedThisDay) {
                        // Delivery Steps Stepper Tracker
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DeliveryStep(label = "Cooking", active = true, completed = true)
                            Spacer(modifier = Modifier.width(4.dp))
                            DeliveryStep(label = "Insulating", active = true, completed = true)
                            Spacer(modifier = Modifier.width(4.dp))
                            DeliveryStep(label = "On the Road", active = true, completed = false)
                            Spacer(modifier = Modifier.width(4.dp))
                            DeliveryStep(label = "Delivered", active = false, completed = false)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Box delivery specs
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFFFEBEE).copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                                .padding(12.dp)
                        ) {
                            Column {
                                Text(
                                    text = "📍 Delivery/Fulfillment: ${sub.deliveryAddress}",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.DarkGray
                                )
                            }
                        }
                    } else {
                        // Paused State Callout
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFECEFF1), RoundedCornerShape(12.dp))
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "📅 You have paused deliveries for $selectedDay. No charges are incurred.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        } else {
            // Unsubscribed Promo Banner
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFB71C1C)),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Subscribe to Gourmet Daily tiffins 🍲",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Receive fresh, authentic premium restaurant meals delivered daily to your doorstep. Includes salad, achaar, and 10 fresh Rotis daily! Special rice and sweets included weekly.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.setTab(AppTab.PROFILE) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFFB71C1C)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("View Subscription Plans", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Weekday selector
        Text(
            text = "Select Delivery Day",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(daysOfWeek) { day ->
                val isSelected = selectedDay == day
                val isPaused = pausedDays[day] ?: false
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedDay = day },
                    label = {
                        Text(
                            text = if (isPaused) "$day ⏸" else day,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFB71C1C),
                        selectedLabelColor = Color.White,
                        containerColor = Color.White,
                        labelColor = Color.DarkGray
                    ),
                    border = BorderStroke(1.dp, if (isSelected) Color(0xFFB71C1C) else Color(0xFFE0E0E0))
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Scheduled meals title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "$selectedDay's Custom Chef Box",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFB71C1C)
                )
                Text(
                    text = "Gourmet menu designed for you",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            if (isSubscriptionActive && !isPausedThisDay) {
                TextButton(
                    onClick = {
                        // Reset all swaps for this day
                        daysOfWeek.forEach { day ->
                            swappedMeals.keys.filter { it.startsWith(day) }.forEach { swappedMeals.remove(it) }
                        }
                    }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Refresh, contentDescription = "Reset menu", modifier = Modifier.size(14.dp), tint = Color(0xFFB71C1C))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Reset Swaps", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFB71C1C))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // If not active subscription, show plan preview selection buttons
        if (!isSubscriptionActive) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFEBEE).copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("Vegetarian Plan", "Non-Veg (Halal) Plan").forEach { plan ->
                    val isSelected = previewPlanName == plan
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) Color(0xFFB71C1C) else Color.Transparent)
                            .clickable { previewPlanName = plan }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (plan == "Vegetarian Plan") "🥬 Vegetarian" else "🍗 Non-Veg (Halal)",
                            color = if (isSelected) Color.White else Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        val activePlanMenu = if (isSubscriptionActive) {
            when (sub.planName) {
                "Vegetarian Plan" -> FoodCatalog.vegWeeklyMenu.firstOrNull { it.day == selectedDay }
                "Non-Veg (Halal) Plan" -> FoodCatalog.nonVegWeeklyMenu.firstOrNull { it.day == selectedDay }
                else -> FoodCatalog.vegWeeklyMenu.firstOrNull { it.day == selectedDay }
            }
        } else {
            when (previewPlanName) {
                "Vegetarian Plan" -> FoodCatalog.vegWeeklyMenu.firstOrNull { it.day == selectedDay }
                "Non-Veg (Halal) Plan" -> FoodCatalog.nonVegWeeklyMenu.firstOrNull { it.day == selectedDay }
                else -> FoodCatalog.vegWeeklyMenu.firstOrNull { it.day == selectedDay }
            }
        }

        if (activePlanMenu != null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFB71C1C).copy(alpha = 0.2f)),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = if (isSubscriptionActive) "ACTIVE DELIVERY DETAILS" else "PREVIEW PLAN MENU",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFFB71C1C)
                            )
                            Text(
                                text = "${activePlanMenu.day}'s Gourmet Box",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.DarkGray
                            )
                        }
                        if (activePlanMenu.special) {
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFFFF8E1), RoundedCornerShape(12.dp))
                                    .border(BorderStroke(1.dp, Color(0xFFFFB300)), RoundedCornerShape(12.dp))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "🌟 Friday Special!",
                                    color = Color(0xFFB78103),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Your daily box contains the following carefully packed items:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    activePlanMenu.dishes.forEachIndexed { index, dish ->
                        val emoji = when {
                            dish.contains("Paneer") || dish.contains("Korma") || dish.contains("Chicken") || dish.contains("Mutton") || dish.contains("Vegetable") || dish.contains("Rajma") || dish.contains("Pakora") || dish.contains("Dal") || dish.contains("Sabzi") || dish.contains("Chana") || dish.contains("Gobi") || dish.contains("Gajar") || dish.contains("Kaddu") -> "🍛"
                            dish.contains("Rotis") -> "🫓"
                            dish.contains("Rice") -> "🍚"
                            dish.contains("Salad") -> "🥗"
                            dish.contains("Achaar") -> "🥭"
                            dish.contains("Sweet") || dish.contains("Dessert") -> "🍰"
                            dish.contains("Drink") || dish.contains("Soda") || dish.contains("Cold") -> "🥤"
                            else -> "🍲"
                        }

                        val itemSwapKey = "${selectedDay}_dish_${index}"
                        val isDishChecked = markedMeals[itemSwapKey] ?: false

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .background(
                                    if (isDishChecked) Color(0xFFFFEBEE).copy(alpha = 0.5f) else Color(0xFFF9F9F7),
                                    RoundedCornerShape(12.dp)
                                )
                                .clickable { markedMeals[itemSwapKey] = !isDishChecked }
                                .padding(horizontal = 16.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                Text(
                                    text = emoji,
                                    fontSize = 24.sp,
                                    modifier = Modifier.padding(end = 12.dp)
                                )
                                Column {
                                    Text(
                                        text = dish,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isDishChecked) Color.Gray else Color.DarkGray,
                                        textDecoration = if (isDishChecked) TextDecoration.LineThrough else null
                                    )
                                    Text(
                                        text = "Included in $selectedDay delivery",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Gray,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                            Checkbox(
                                checked = isDishChecked,
                                onCheckedChange = { markedMeals[itemSwapKey] = it },
                                colors = CheckboxDefaults.colors(checkedColor = Color(0xFFB71C1C))
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    if (isSubscriptionActive) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val context = LocalContext.current
                            Button(
                                onClick = {
                                    val textMsg = "Hi Happy Foods, I have a question about my ${sub.planName} box for ${selectedDay}!"
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/351965712949?text=${Uri.encode(textMsg)}"))
                                    context.startActivity(intent)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEBEE), contentColor = Color(0xFFB71C1C)),
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(Icons.Default.SupportAgent, contentDescription = "Support", modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Support", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }

                            Button(
                                onClick = {
                                    Toast.makeText(context, "Log submitted successfully! Keep eating happy!", Toast.LENGTH_SHORT).show()
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C)),
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(Icons.Default.Check, contentDescription = "Log", modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Log Box Eaten", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    } else {
                        Button(
                            onClick = { viewModel.setTab(AppTab.PROFILE) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C)),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Subscribe to ${if (previewPlanName == "Vegetarian Plan") "Vegetarian Plan (€120/mo)" else "Non-Veg Plan (€150/mo)"} 🚀",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

        // --- WEEKLY MEAL PLAN (Mon - Sun) Section ---
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "📅 WEEKLY MEAL PLAN (MON - SUN)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFB71C1C)
        )
        Text(
            text = "Authentic, freshly cooked meals delivered daily to your doorstep",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFB71C1C).copy(alpha = 0.15f)),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                daysOfWeek.forEach { dayName ->
                    val vegMenu = FoodCatalog.vegWeeklyMenu.firstOrNull { it.day == dayName }
                    val nonVegMenu = FoodCatalog.nonVegWeeklyMenu.firstOrNull { it.day == dayName }

                    var isExpanded by remember { mutableStateOf(dayName == "Monday") }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isExpanded = !isExpanded }
                            .padding(vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (dayName == "Friday") Icons.Default.Star else Icons.Default.CalendarToday,
                                    contentDescription = null,
                                    tint = if (dayName == "Friday") Color(0xFFD4AF37) else Color(0xFFB71C1C),
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = dayName,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = if (dayName == "Friday") Color(0xFFB71C1C) else Color.DarkGray
                                )
                                if (dayName == "Friday") {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Box(
                                        modifier = Modifier
                                            .background(Color(0xFFFFF8E1), RoundedCornerShape(6.dp))
                                            .border(BorderStroke(1.dp, Color(0xFFFFB300)), RoundedCornerShape(6.dp))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text("FRIDAY SPECIAL 🌟", color = Color(0xFFB78103), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        if (isExpanded) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                if (vegMenu != null) {
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .background(Color(0xFFFFF1F1), RoundedCornerShape(12.dp))
                                            .border(BorderStroke(1.dp, Color(0xFFB71C1C).copy(alpha = 0.08f)), RoundedCornerShape(12.dp))
                                            .padding(10.dp)
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text("🥬", fontSize = 14.sp)
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text("Vegetarian", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color(0xFFB71C1C))
                                        }
                                        Spacer(modifier = Modifier.height(6.dp))
                                        vegMenu.dishes.forEach { dish ->
                                            Text("• $dish", fontSize = 11.sp, color = Color.DarkGray, modifier = Modifier.padding(vertical = 1.dp))
                                        }
                                    }
                                }

                                if (nonVegMenu != null) {
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .background(Color(0xFFFFFDF5), RoundedCornerShape(12.dp))
                                            .border(BorderStroke(1.dp, Color(0xFFD4AF37).copy(alpha = 0.15f)), RoundedCornerShape(12.dp))
                                            .padding(10.dp)
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text("🍗", fontSize = 14.sp)
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text("Non-Veg (Halal)", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color(0xFFB71C1C))
                                        }
                                        Spacer(modifier = Modifier.height(6.dp))
                                        nonVegMenu.dishes.forEach { dish ->
                                            Text("• $dish", fontSize = 11.sp, color = Color.DarkGray, modifier = Modifier.padding(vertical = 1.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (dayName != "Sunday") {
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray.copy(alpha = 0.3f)))
                    }
                }
            }
        }
    }

    // Swapping Dialog
    showSwapDialogForCategory?.let { category ->
        val currentDay = showSwapDialogDay ?: "Monday"
        val altMeals = FoodCatalog.meals.filter { it.category == category }

        Dialog(onDismissRequest = { showSwapDialogForCategory = null }) {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF2E7D32))
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Swap $category for $currentDay",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(altMeals) { alternativeMeal ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        swappedMeals["${currentDay}_${category}"] = alternativeMeal
                                        showSwapDialogForCategory = null
                                    },
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F7)),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(getMealImageUrl(alternativeMeal.id))
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = alternativeMeal.title,
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(RoundedCornerShape(8.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            alternativeMeal.title,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = Color.DarkGray
                                        )
                                        Text(
                                            "Diet: ${alternativeMeal.diet}  |  ${alternativeMeal.calories} kcal",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                    }
                                    Icon(
                                        Icons.Default.ChevronRight,
                                        contentDescription = "Select",
                                        tint = Color(0xFF2E7D32)
                                    )
                                }
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { showSwapDialogForCategory = null }) {
                            Text("Cancel", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DeliveryStep(label: String, active: Boolean, completed: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(75.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(
                    color = if (completed) Color(0xFF2E7D32) else if (active) Color(0xFFFFB74D) else Color(0xFFECEFF1),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (completed) {
                Icon(Icons.Default.Check, contentDescription = "Done", tint = Color.White, modifier = Modifier.size(14.dp))
            } else {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(Color.White, CircleShape)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 9.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (active || completed) Color.DarkGray else Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExploreScreen(viewModel: MainViewModel, userPref: UserPreference) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val goalFilter by viewModel.selectedGoalFilter.collectAsState()
    val dietFilter by viewModel.selectedDietFilter.collectAsState()
    val savedMealsList by viewModel.savedMeals.collectAsState()

    var selectedMealForDetail by remember { mutableStateOf<CatalogMeal?>(null) }

    val filteredMeals = FoodCatalog.meals.filter { meal ->
        val matchesSearch = meal.title.contains(searchQuery, ignoreCase = true) ||
                meal.ingredients.any { it.contains(searchQuery, ignoreCase = true) }
        val matchesGoal = goalFilter == "All" || meal.goals.contains(goalFilter)
        val matchesDiet = dietFilter == "All" || meal.diet == dietFilter

        matchesSearch && matchesGoal && matchesDiet
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            placeholder = { Text("Search culinary delicacies or ingredients...", fontSize = 14.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.updateSearchQuery("") }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear search", tint = Color.Gray)
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("recipe_search_input"),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF2E7D32),
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Goal Filters row
        Text("Browse by Plan Goal", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color.Gray)
        Spacer(modifier = Modifier.height(6.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val goals = listOf("All", "Weight Loss", "Muscle Gain", "Healthy Living", "Balanced")
            goals.forEach { goal ->
                FilterChip(
                    selected = goalFilter == goal,
                    onClick = { viewModel.setGoalFilter(goal) },
                    label = { Text(goal, fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF2E7D32),
                        selectedLabelColor = Color.White
                    ),
                    border = BorderStroke(1.dp, if (goalFilter == goal) Color(0xFF2E7D32) else Color(0xFFE0E0E0))
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Diet Filters row
        Text("Dietary Filters", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color.Gray)
        Spacer(modifier = Modifier.height(6.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val diets = listOf("All", "Vegan", "Vegetarian", "Keto", "Gluten-Free", "Halal")
            diets.forEach { diet ->
                FilterChip(
                    selected = dietFilter == diet,
                    onClick = { viewModel.setDietFilter(diet) },
                    label = { Text(diet, fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF2E7D32),
                        selectedLabelColor = Color.White
                    ),
                    border = BorderStroke(1.dp, if (dietFilter == diet) Color(0xFF2E7D32) else Color(0xFFE0E0E0))
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Chef's Kitchen Catalog (${filteredMeals.size} dishes)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (filteredMeals.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.Restaurant,
                        contentDescription = "No items",
                        modifier = Modifier.size(64.dp),
                        tint = Color.LightGray
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "No recipes found in catalog.",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredMeals) { meal ->
                    val isFav = savedMealsList.any { it.id == meal.id }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedMealForDetail = meal }
                            .testTag("recipe_card_${meal.id}"),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(1.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column {
                            Box {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(getMealImageUrl(meal.id))
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = meal.title,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(170.dp),
                                    contentScale = ContentScale.Crop
                                )
                                // Badges
                                Row(
                                    modifier = Modifier
                                        .align(Alignment.TopStart)
                                        .padding(12.dp),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(Color(0xFF2E7D32), RoundedCornerShape(8.dp))
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(meal.category.uppercase(), color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Black)
                                    }
                                    if (meal.diet != "None") {
                                        Box(
                                            modifier = Modifier
                                                .background(Color(0xFF81C784), RoundedCornerShape(8.dp))
                                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                        ) {
                                            Text(meal.diet, color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                }

                                // Favorite Button
                                IconButton(
                                    onClick = { viewModel.toggleFavorite(meal) },
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(8.dp)
                                        .background(Color.White.copy(alpha = 0.9f), CircleShape)
                                        .size(36.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                        contentDescription = "Favorite",
                                        tint = if (isFav) Color.Red else Color.Gray,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }

                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    meal.title,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "⚡ Energy: ${meal.calories} kcal  |  💪 P: ${meal.protein}g  C: ${meal.carbs}g  F: ${meal.fats}g",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Recipe Detail Dialog
    selectedMealForDetail?.let { meal ->
        var isFav = savedMealsList.any { it.id == meal.id }
        val checkedIngredients = remember { mutableStateMapOf<String, Boolean>() }

        Dialog(onDismissRequest = { selectedMealForDetail = null }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(getMealImageUrl(meal.id))
                                .crossfade(true)
                                .build(),
                            contentDescription = meal.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(210.dp),
                            contentScale = ContentScale.Crop
                        )
                        IconButton(
                            onClick = { selectedMealForDetail = null },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp)
                                .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                        }
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                            .padding(20.dp)
                    ) {
                        Text(
                            meal.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF1B5E20)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFE8F5E9), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(meal.category, color = Color(0xFF2E7D32), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            if (meal.diet != "None") {
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFFF1F8E9), RoundedCornerShape(8.dp))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(meal.diet, color = Color(0xFF558B2F), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Macros facts
                        Text("Nutritional Value", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))
                        MacroGauge(label = "Calories", amount = "${meal.calories} kcal", percent = 1f, color = Color.Gray)
                        MacroGauge(label = "Protein", amount = "${meal.protein} g", percent = meal.protein / 50f, color = Color(0xFF2E7D32))
                        MacroGauge(label = "Carbohydrates", amount = "${meal.carbs} g", percent = meal.carbs / 250f, color = Color(0xFFFFB74D))
                        MacroGauge(label = "Fats", amount = "${meal.fats} g", percent = meal.fats / 80f, color = Color(0xFFE57373))

                        Spacer(modifier = Modifier.height(20.dp))

                        Text("Fresh Ingredients", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        meal.ingredients.forEach { ingredient ->
                            val isChecked = checkedIngredients[ingredient] ?: false
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { checkedIngredients[ingredient] = !isChecked }
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = { checkedIngredients[ingredient] = it },
                                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF2E7D32))
                                )
                                Text(
                                    ingredient,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (isChecked) Color.LightGray else Color.DarkGray
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text("Chef's Cooking Instructions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        meal.instructions.forEachIndexed { idx, step ->
                            Row(modifier = Modifier.padding(vertical = 6.dp)) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(Color(0xFF2E7D32), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("${idx + 1}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(step, style = MaterialTheme.typography.bodyMedium, color = Color.DarkGray, modifier = Modifier.weight(1f))
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = { viewModel.toggleFavorite(meal) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, Color(0xFF2E7D32))
                        ) {
                            Icon(
                                imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = if (isFav) Color.Red else Color(0xFF2E7D32)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (isFav) "Favorited" else "Add to Favorites", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = { selectedMealForDetail = null },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
                        ) {
                            Text("Done", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MacroGauge(label: String, amount: String, percent: Float, color: Color) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
            Text(amount, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = if (color == Color.Gray) Color.DarkGray else color)
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = percent.coerceIn(0f, 1f),
            modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape),
            color = if (color == Color.Gray) Color(0xFF2E7D32) else color,
            trackColor = Color(0xFFECEFF1)
        )
    }
}

@Composable
fun ChefScreen(viewModel: MainViewModel, pref: UserPreference) {
    val chatList by viewModel.chatHistory.collectAsState()
    val isAiLoading by viewModel.isAiLoading.collectAsState()

    var inputMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F7))
    ) {
        // AI Chef Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(Color(0xFFE8F5E9), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.SmartToy,
                            contentDescription = "Chef",
                            tint = Color(0xFF2E7D32),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "AI Kitchen Assistant 🧑‍🍳",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1B5E20)
                        )
                        Text(
                            text = "Ask about meal swaps, delivery help, and allergens",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
                IconButton(onClick = { viewModel.clearChatHistory() }) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "Clear chat history",
                        tint = Color.Gray
                    )
                }
            }
        }

        // Suggestion Chips
        val suggestions = listOf(
            "What is today's keto option?",
            "Can I swap the salad dressing?",
            "Change delivery to evening",
            "What organic benefits do you offer?"
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(suggestions) { query ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.clickable { viewModel.sendMessageToChef(query) },
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFFE8F5E9))
                ) {
                    Text(
                        text = query,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF2E7D32)
                    )
                }
            }
        }

        // Chat Bubble list
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            reverseLayout = false
        ) {
            items(chatList) { msg ->
                ChatBubble(message = msg)
                Spacer(modifier = Modifier.height(10.dp))
            }
            if (isAiLoading) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(16.dp),
                                    strokeWidth = 2.dp,
                                    color = Color(0xFF2E7D32)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Happy Chef is typing...", fontSize = 12.sp, color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }

        // Text input bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = inputMsg,
                    onValueChange = { inputMsg = it },
                    placeholder = { Text("Ask about custom requests, delivery modifications...", fontSize = 13.sp) },
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("chef_chat_input"),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2E7D32),
                        unfocusedBorderColor = Color(0xFFE0E0E0)
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (inputMsg.isNotBlank()) {
                            viewModel.sendMessageToChef(inputMsg)
                            inputMsg = ""
                        }
                    },
                    modifier = Modifier
                        .background(Color(0xFF2E7D32), CircleShape)
                        .size(44.dp)
                ) {
                    Icon(
                        Icons.Default.Send,
                        contentDescription = "Send",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val alignEnd = message.isUser
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (alignEnd) Arrangement.End else Arrangement.Start
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (alignEnd) Color(0xFF2E7D32) else Color.White
            ),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (alignEnd) 16.dp else 0.dp,
                bottomEnd = if (alignEnd) 0.dp else 16.dp
            ),
            elevation = CardDefaults.cardElevation(1.dp),
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(
                text = message.message,
                modifier = Modifier.padding(12.dp),
                color = if (alignEnd) Color.White else Color.DarkGray,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ProfileScreen(viewModel: MainViewModel, pref: UserPreference, sub: SubscriptionPlan) {
    val context = LocalContext.current
    var nameInput by remember { mutableStateOf(pref.name) }
    var goalInput by remember { mutableStateOf(pref.goal) }
    var dietInput by remember { mutableStateOf(pref.diet) }

    // Delivery defaults to "Praceta Prof. Francisco Gentil 10" if empty
    var addressInput by remember { mutableStateOf(if (sub.deliveryAddress.isBlank()) "Praceta Prof. Francisco Gentil 10" else sub.deliveryAddress) }
    var timeslotInput by remember { mutableStateOf(if (sub.deliveryTime.isBlank()) "12:00 PM (Lunch)" else sub.deliveryTime) }

    // Selected plan and quantity
    var selectedPlanName by remember { mutableStateOf(if (sub.planName == "Free Basic Plan" || sub.planName.isBlank()) "Vegetarian Plan" else sub.planName) }
    var quantity by remember { mutableStateOf(1) }
    var fulfillmentMode by remember { mutableStateOf("Delivery") } // "Self Pick-Up" or "Delivery"

    val showPlanOptions = FoodCatalog.businessPlans.filter { it.name != "Free Basic Plan" }

    // Find details of the selected plan
    val currentSelectedPlan = showPlanOptions.firstOrNull { it.name == selectedPlanName } ?: showPlanOptions.first()

    val deliveryCost = if (fulfillmentMode == "Delivery" && quantity < 5) 30.0 else 0.0
    val totalCost = (currentSelectedPlan.price * quantity) + deliveryCost

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // App header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo_1782662261235),
                contentDescription = "Happy Foods Portugal Logo",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(BorderStroke(1.dp, Color(0xFFB71C1C).copy(alpha = 0.2f)), RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Happy Foods Portugal 🇵🇹",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFFB71C1C)
                )
                Text(
                    text = "Configure your recurring everyday food deliveries",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Subscription overview card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("ACTIVE SUBSCRIPTION", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                        Text(sub.planName, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFFB71C1C))
                    }
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFFEBEE), RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = if (sub.status == "Active" && sub.planName != "Free Basic Plan") "Active" else "Inactive",
                            color = Color(0xFFB71C1C),
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = if (sub.price == 0.0) "Price: Free" else "Price: €${sub.price.toInt()} / month",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Text(
                    text = "Fulfillment: ${if (sub.deliveryAddress.contains("Pick-Up") || sub.deliveryAddress.contains("pick")) "Self Pick-Up" else "Delivery to " + sub.deliveryAddress}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                if (sub.planName != "Free Basic Plan") {
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedButton(
                        onClick = { viewModel.cancelSubscription() },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFD32F2F)),
                        border = BorderStroke(1.dp, Color(0xFFD32F2F)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Pause / Cancel Subscription", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Upgrade Options section
        Text(
            text = "Select Subscription Plan 🍲",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))

        showPlanOptions.forEach { plan ->
            val isPlanSelected = selectedPlanName == plan.name
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        selectedPlanName = plan.name
                    },
                colors = CardDefaults.cardColors(
                    containerColor = if (isPlanSelected) Color(0xFFFFEBEE).copy(alpha = 0.5f) else Color.White
                ),
                border = BorderStroke(2.dp, if (isPlanSelected) Color(0xFFB71C1C) else Color(0xFFE0E0E0)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = isPlanSelected,
                                onClick = { selectedPlanName = plan.name },
                                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFB71C1C))
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(plan.name, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = Color.DarkGray)
                        }
                        Text("€${plan.price.toInt()}/month", fontWeight = FontWeight.Bold, color = Color(0xFFB71C1C))
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(plan.description, fontSize = 11.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.height(8.dp))
                    plan.features.take(3).forEach { feat ->
                        Text("✓ $feat", fontSize = 11.sp, color = Color.DarkGray)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Subscription Quantity
        Text(
            text = "Number of Subscriptions 👥",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(BorderStroke(1.dp, Color(0xFFE0E0E0)), RoundedCornerShape(12.dp))
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Quantity", fontWeight = FontWeight.Bold, color = Color.DarkGray)
                Text("Order up to 5 subscriptions for free delivery", fontSize = 11.sp, color = Color.Gray)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { if (quantity > 1) quantity-- },
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFF5F5F5), CircleShape)
                ) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease", tint = Color.DarkGray)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = quantity.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(
                    onClick = { quantity++ },
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFF5F5F5), CircleShape)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Increase", tint = Color.DarkGray)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Fulfillment options selector
        Text(
            text = "Delivery or Self Pick-Up 🛵",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Delivery option
            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable { fulfillmentMode = "Delivery" },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (fulfillmentMode == "Delivery") Color(0xFFFFEBEE).copy(alpha = 0.5f) else Color.White
                ),
                border = BorderStroke(
                    2.dp,
                    if (fulfillmentMode == "Delivery") Color(0xFFB71C1C) else Color(0xFFE0E0E0)
                )
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.DeliveryDining, contentDescription = "Delivery", tint = Color(0xFFB71C1C), modifier = Modifier.size(28.dp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Delivery", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.DarkGray)
                    Text("€30/month", fontSize = 11.sp, color = Color.Gray)
                }
            }

            // Self Pick-Up option
            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable { fulfillmentMode = "Self Pick-Up" },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (fulfillmentMode == "Self Pick-Up") Color(0xFFFFEBEE).copy(alpha = 0.5f) else Color.White
                ),
                border = BorderStroke(
                    2.dp,
                    if (fulfillmentMode == "Self Pick-Up") Color(0xFFB71C1C) else Color(0xFFE0E0E0)
                )
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Restaurant, contentDescription = "Self Pick-Up", tint = Color(0xFFB71C1C), modifier = Modifier.size(28.dp))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Self Pick-Up", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.DarkGray)
                    Text("FREE", fontSize = 11.sp, color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Delivery info notice
        if (fulfillmentMode == "Delivery") {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFF9F9), RoundedCornerShape(12.dp))
                    .border(BorderStroke(1.dp, Color(0xFFB71C1C).copy(alpha = 0.1f)), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Column {
                    Text(
                        text = "📍 Delivery Information:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color(0xFFB71C1C)
                    )
                    Text(
                        text = "Delivery is €30/month around Praceta Prof. Francisco Gentil 10. If you order 5 or more subscriptions, you get FREE delivery!",
                        fontSize = 11.sp,
                        color = Color.DarkGray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = addressInput,
                onValueChange = { addressInput = it },
                label = { Text("Delivery Street Address") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFB71C1C),
                    unfocusedBorderColor = Color(0xFFE0E0E0)
                )
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF9F9F9), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Text(
                    text = "🏪 Pick-Up Location: Praceta Prof. Francisco Gentil 10, Ramada, Odivelas. Please coordinate pick-up times with us on WhatsApp.",
                    fontSize = 11.sp,
                    color = Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = timeslotInput,
            onValueChange = { timeslotInput = it },
            label = { Text("Preferred Delivery/Pick-Up Timeslot") },
            placeholder = { Text("e.g. 12:00 PM (Lunch)") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFB71C1C),
                unfocusedBorderColor = Color(0xFFE0E0E0)
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Contacts list from website
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFDF5)),
            border = BorderStroke(1.dp, Color(0xFFD4AF37).copy(alpha = 0.25f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "📞 Delivery Hotline & Support (From Website)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color(0xFFB71C1C)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Phone, contentDescription = "Phone", tint = Color(0xFFB71C1C), modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Primary (WhatsApp): +351 965 712 949", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
                    }
                    IconButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+351965712949"))
                            context.startActivity(intent)
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = "Dial", tint = Color(0xFFB71C1C), modifier = Modifier.size(14.dp))
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Phone, contentDescription = "Phone", tint = Color(0xFFB71C1C), modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Secondary: +351 965 712 948", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
                    }
                    IconButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+351965712948"))
                            context.startActivity(intent)
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = "Dial", tint = Color(0xFFB71C1C), modifier = Modifier.size(14.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Culinary Profile
        Text(
            text = "Your Culinary Profile 🥗",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Full Name") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFB71C1C),
                unfocusedBorderColor = Color(0xFFE0E0E0)
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = goalInput,
            onValueChange = { goalInput = it },
            label = { Text("Dietary/Goal Plan (e.g. Healthy Living)") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFB71C1C),
                unfocusedBorderColor = Color(0xFFE0E0E0)
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = dietInput,
            onValueChange = { dietInput = it },
            label = { Text("Allergies / Restrictions (e.g. None, Halal, Keto)") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFB71C1C),
                unfocusedBorderColor = Color(0xFFE0E0E0)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Total Pricing Overview
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE).copy(alpha = 0.2f)),
            border = BorderStroke(1.dp, Color(0xFFB71C1C).copy(alpha = 0.15f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("ORDER SUMMARY", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("$selectedPlanName x $quantity", fontSize = 13.sp, color = Color.DarkGray)
                    Text("€${(currentSelectedPlan.price * quantity).toInt()}", fontSize = 13.sp, color = Color.DarkGray)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Fulfillment: $fulfillmentMode", fontSize = 13.sp, color = Color.Gray)
                    Text(if (deliveryCost == 0.0) "FREE" else "€${deliveryCost.toInt()}", fontSize = 13.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray.copy(alpha = 0.3f)))
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Total Price:", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.DarkGray)
                    Text("€${totalCost.toInt()}/month", fontWeight = FontWeight.Black, fontSize = 18.sp, color = Color(0xFFB71C1C))
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Order Now Button (which replaces the Save Profiles button)
        Button(
            onClick = {
                viewModel.updateProfile(
                    name = nameInput,
                    goal = goalInput,
                    diet = dietInput,
                    calories = pref.dailyCalorieTarget
                )
                // Save subscription local model
                viewModel.updateSubscription(
                    planName = selectedPlanName,
                    price = totalCost,
                    address = if (fulfillmentMode == "Self Pick-Up") "Self Pick-Up at Ramada" else addressInput,
                    days = "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday",
                    time = timeslotInput
                )

                // Trigger WhatsApp order message
                val whatsappMsg = """
                    New Subscription Order — Happy Foods Portugal 🇵🇹
                    
                    Name: $nameInput
                    Diet Preference: $dietInput
                    Plan Selected: $selectedPlanName (€${currentSelectedPlan.price.toInt()}/month)
                    Quantity ordered: $quantity
                    Fulfillment: $fulfillmentMode
                    Address: ${if (fulfillmentMode == "Self Pick-Up") "Praceta Prof. Francisco Gentil 10, Ramada, Odivelas" else addressInput}
                    Fulfillment Timeslot: $timeslotInput
                    
                    Billing Summary:
                    - Subscriptions: $quantity x €${currentSelectedPlan.price.toInt()} = €${(currentSelectedPlan.price * quantity).toInt()}/month
                    - Delivery fee: €${deliveryCost.toInt()}/month
                    - Total Bill: €${totalCost.toInt()}/month
                    
                    Thank you! Please process my order.
                """.trimIndent()

                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/351965712949?text=${Uri.encode(whatsappMsg)}"))
                    context.startActivity(intent)
                    Toast.makeText(context, "Redirecting to WhatsApp to place your order...", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(context, "Order saved! Call us at +351 965 712 949 to confirm.", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier.fillMaxWidth().testTag("place_order_button"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C))
        ) {
            Text("Confirm & Place Order Now 🚀", fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
        }
    }
}
