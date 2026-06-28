package com.example.data

data class CatalogMeal(
    val id: String,
    val title: String,
    val category: String, // "Breakfast", "Lunch", "Dinner", "Snack"
    val calories: Int,
    val protein: Int, // in grams
    val carbs: Int, // in grams
    val fats: Int, // in grams
    val diet: String, // "None", "Vegan", "Vegetarian", "Keto", "Gluten-Free", "Halal"
    val goals: List<String>, // "Weight Loss", "Muscle Gain", "Healthy Living", "Balanced"
    val imageUrl: String,
    val ingredients: List<String>,
    val instructions: List<String>
)

data class BusinessPlanInfo(
    val name: String,
    val price: Double,
    val description: String,
    val features: List<String>,
    val badge: String? = null
)

object FoodCatalog {
    val businessPlans = listOf(
        BusinessPlanInfo(
            name = "Free Basic Plan",
            price = 0.0,
            description = "Start your journey with calorie tracking and recipe exploration.",
            features = listOf(
                "Access to healthy recipes",
                "Personalized nutrition settings",
                "Simple calorie goal tracking",
                "Basic AI Chef advice"
            )
        ),
        BusinessPlanInfo(
            name = "Vegetarian Plan",
            price = 120.0,
            description = "7 days a week fresh daily Vegetarian (Veg) restaurant meal delivery.",
            features = listOf(
                "Fresh daily lunch/dinner delivery",
                "Authentic gourmet vegetarian dishes",
                "Salad, Achaar & 10 Rotis included daily",
                "Wednesday Special: Jeera Rice, Friday Special: Sweet & Drink",
                "Contactless doorstep delivery or self-pickup"
            ),
            badge = "Popular"
        ),
        BusinessPlanInfo(
            name = "Non-Veg (Halal) Plan",
            price = 150.0,
            description = "7 days a week premium daily Halal non-vegetarian restaurant meal delivery.",
            features = listOf(
                "Fresh daily delicious Halal meat dishes",
                "Certified Halal Chicken, Mutton, and Veg entrees",
                "Salad, Achaar & 10 Rotis included daily",
                "Wednesday Special: Jeera Rice, Friday Special: Sweet & Drink",
                "Contactless doorstep delivery or self-pickup"
            ),
            badge = "Best Value"
        )
    )

    val meals = listOf(
        CatalogMeal(
            id = "butter_chicken",
            title = "Butter Chicken",
            category = "Dinner",
            calories = 520,
            protein = 35,
            carbs = 10,
            fats = 28,
            diet = "Halal",
            goals = listOf("Muscle Gain", "Balanced", "Healthy Living"),
            imageUrl = "butter_chicken",
            ingredients = listOf(
                "Boneless Halal Chicken breast/thighs",
                "Spiced Tomato & Cashew gravy",
                "Heavy fresh cream",
                "Pure butter",
                "Garam masala & kasuri methi"
            ),
            instructions = listOf(
                "Sauté marinated chicken chunks until golden brown.",
                "Simmer slowly in a luscious spiced tomato, cashew, and butter-infused gravy.",
                "Finish with a luxurious swirl of heavy cream and fragrant fenugreek leaves."
            )
        ),
        CatalogMeal(
            id = "matar_paneer",
            title = "Matar Paneer",
            category = "Lunch",
            calories = 410,
            protein = 18,
            carbs = 16,
            fats = 24,
            diet = "Vegetarian",
            goals = listOf("Balanced", "Healthy Living"),
            imageUrl = "matar_paneer",
            ingredients = listOf(
                "Fresh Cottage Cheese (Paneer) cubes",
                "Sweet green peas",
                "Onion-tomato gravy base",
                "Ginger-garlic paste",
                "Traditional Indian spices"
            ),
            instructions = listOf(
                "Pan-fry the paneer cubes until lightly golden.",
                "Prepare onion-tomato gravy with ginger-garlic and spices.",
                "Simmer paneer and sweet peas in the gravy until tender."
            )
        ),
        CatalogMeal(
            id = "chicken_korma",
            title = "Chicken Korma",
            category = "Dinner",
            calories = 490,
            protein = 33,
            carbs = 9,
            fats = 26,
            diet = "Halal",
            goals = listOf("Muscle Gain", "Balanced"),
            imageUrl = "chicken_korma",
            ingredients = listOf(
                "Halal Chicken pieces",
                "Yogurt & caramelized onion paste",
                "Korma spice blend (cardamom, clove, cinnamon)",
                "Ginger and garlic",
                "Almond meal for richness"
            ),
            instructions = listOf(
                "Caramelize onions and blend with creamy yogurt.",
                "Cook chicken pieces with korma spices and ginger-garlic paste.",
                "Slow-cook the mixture with onion-yogurt paste until oil separates."
            )
        ),
        CatalogMeal(
            id = "mutton_korma",
            title = "Mutton Korma",
            category = "Dinner",
            calories = 580,
            protein = 38,
            carbs = 8,
            fats = 36,
            diet = "Halal",
            goals = listOf("Muscle Gain", "Balanced"),
            imageUrl = "mutton_korma",
            ingredients = listOf(
                "Tender Halal Mutton chunks",
                "Yogurt and brown onion paste",
                "Aromatic spices & saffron essence",
                "Kewra water & ginger juliennes"
            ),
            instructions = listOf(
                "Pressure-cook or slow-braise mutton chunks with ginger-garlic and ground spices.",
                "Incorporate the traditional caramelized onion and yogurt gravy.",
                "Simmer on low heat until meat is falling-off-the-bone tender."
            )
        ),
        CatalogMeal(
            id = "palak_paneer",
            title = "Palak Paneer",
            category = "Lunch",
            calories = 380,
            protein = 19,
            carbs = 12,
            fats = 22,
            diet = "Vegetarian",
            goals = listOf("Weight Loss", "Balanced", "Healthy Living"),
            imageUrl = "palak_paneer",
            ingredients = listOf(
                "Fresh Spinach (Palak) leaves",
                "Paneer cubes",
                "Green chilies, ginger, and garlic",
                "Kasuri methi and fresh cream"
            ),
            instructions = listOf(
                "Blanch spinach leaves and purée with green chilies.",
                "Sauté ginger-garlic and spices, then add the spinach purée.",
                "Fold in paneer cubes and simmer gently, garnishing with cream."
            )
        ),
        CatalogMeal(
            id = "rajma",
            title = "Rajma Masala",
            category = "Lunch",
            calories = 340,
            protein = 14,
            carbs = 48,
            fats = 8,
            diet = "Vegan",
            goals = listOf("Weight Loss", "Balanced", "Healthy Living"),
            imageUrl = "rajma",
            ingredients = listOf(
                "Red kidney beans (Rajma)",
                "Finely chopped onions & tomatoes",
                "Kashmiri red chili & amchur powder",
                "Coriander leaves"
            ),
            instructions = listOf(
                "Soak kidney beans overnight and pressure-cook until buttery soft.",
                "Prepare a tangy onion-tomato masala base with spices.",
                "Add cooked beans with their broth and simmer until thick and delicious."
            )
        ),
        CatalogMeal(
            id = "curry_pakora",
            title = "Curry Pakora",
            category = "Lunch",
            calories = 420,
            protein = 15,
            carbs = 42,
            fats = 18,
            diet = "Vegetarian",
            goals = listOf("Balanced", "Healthy Living"),
            imageUrl = "curry_pakora",
            ingredients = listOf(
                "Sour yogurt",
                "Gram flour (besan)",
                "Onion & potato fritters (pakoras)",
                "Tarka spices (mustard seeds, curry leaves, red chilies)"
            ),
            instructions = listOf(
                "Whisk yogurt and gram flour with water and spices to make a smooth liquid.",
                "Boil and simmer the yogurt mixture for an hour to develop deep flavors.",
                "Drop in freshly fried pakoras and temper (tarka) with hot oil and spices."
            )
        ),
        CatalogMeal(
            id = "shahi_tarka_dal",
            title = "Shahi Tarka Dal",
            category = "Dinner",
            calories = 310,
            protein = 16,
            carbs = 44,
            fats = 7,
            diet = "Vegan",
            goals = listOf("Weight Loss", "Balanced", "Healthy Living"),
            imageUrl = "shahi_tarka_dal",
            ingredients = listOf(
                "Yellow lentils (Moong & Masoor dal)",
                "Garlic, cumin, and dry red chilies",
                "Desi ghee or vegetable oil",
                "Fresh coriander"
            ),
            instructions = listOf(
                "Boil lentils with turmeric and salt until smooth and creamy.",
                "Prepare the golden tarka by frying minced garlic and cumin in ghee.",
                "Pour the sizzling aromatic tarka directly over the boiling lentils."
            )
        ),
        CatalogMeal(
            id = "aloo_gobi",
            title = "Aloo Gobi",
            category = "Dinner",
            calories = 280,
            protein = 6,
            carbs = 32,
            fats = 9,
            diet = "Vegan",
            goals = listOf("Weight Loss", "Healthy Living"),
            imageUrl = "aloo_gobi",
            ingredients = listOf(
                "Fresh cauliflower florets (Gobi)",
                "Potato cubes (Aloo)",
                "Turmeric, ginger juliennes, and green chilies",
                "Amchur (mango powder) & coriander"
            ),
            instructions = listOf(
                "Sauté cumin, ginger, and green chilies in a pan.",
                "Toss in potatoes and cauliflower with spices.",
                "Cover and cook on low heat in its own steam until tender and crispy."
            )
        ),
        CatalogMeal(
            id = "chicken_aloo_matar",
            title = "Chicken Aloo Matar",
            category = "Lunch",
            calories = 460,
            protein = 32,
            carbs = 18,
            fats = 20,
            diet = "Halal",
            goals = listOf("Muscle Gain", "Balanced"),
            imageUrl = "chicken_aloo_matar",
            ingredients = listOf(
                "Halal Chicken pieces",
                "Potato cubes",
                "Green peas",
                "Onion-tomato shorba gravy base"
            ),
            instructions = listOf(
                "Sauté chicken with ginger-garlic paste and whole spices.",
                "Add potato cubes and green peas with onion-tomato paste.",
                "Simmer with water to form a warm, comforting light curry broth (shorba)."
            )
        ),
        CatalogMeal(
            id = "shahi_nutri_korma",
            title = "Shahi Nutri Korma",
            category = "Lunch",
            calories = 360,
            protein = 24,
            carbs = 20,
            fats = 14,
            diet = "Vegetarian",
            goals = listOf("Muscle Gain", "Balanced", "Healthy Living"),
            imageUrl = "shahi_nutri_korma",
            ingredients = listOf(
                "High-protein Soya chunks",
                "Yogurt and onion gravy base",
                "Whole aromatic korma spices",
                "Kewra essence & cream"
            ),
            instructions = listOf(
                "Boil soya chunks, squeeze out excess water, and pan-sear.",
                "Sauté korma spices, then simmer soy chunks in spiced yogurt-onion gravy.",
                "Finish with a drop of kewra essence and a garnish of fresh ginger."
            )
        ),
        CatalogMeal(
            id = "mix_vegetable",
            title = "Mix Vegetable (Sabzi)",
            category = "Lunch",
            calories = 260,
            protein = 5,
            carbs = 28,
            fats = 8,
            diet = "Vegan",
            goals = listOf("Weight Loss", "Healthy Living"),
            imageUrl = "mix_vegetable",
            ingredients = listOf(
                "Seasonal mixed vegetables (carrots, beans, peas, potato)",
                "Cumin seeds and ginger-garlic",
                "Turmeric, coriander, and garam masala"
            ),
            instructions = listOf(
                "Sauté cumin seeds and ginger in a large pan.",
                "Stir in all diced vegetables with spices and salt.",
                "Slow-cook covered on low-heat until the vegetables are cooked yet firm."
            )
        ),
        CatalogMeal(
            id = "black_chana",
            title = "Kala Chana Masala (Black Chana)",
            category = "Lunch",
            calories = 320,
            protein = 13,
            carbs = 44,
            fats = 7,
            diet = "Vegan",
            goals = listOf("Weight Loss", "Balanced", "Healthy Living"),
            imageUrl = "black_chana",
            ingredients = listOf(
                "Soaked Black chickpeas",
                "Tangy onion-tomato masala",
                "Cumin, coriander, and dry mango powder"
            ),
            instructions = listOf(
                "Boil black chickpeas with salt until tender.",
                "Cook a seasoned masala of onion, tomato, ginger, and spices.",
                "Simmer the boiled chana in the masala until highly aromatic and dry."
            )
        ),
        CatalogMeal(
            id = "white_chana",
            title = "Chana Masala (White Chana)",
            category = "Dinner",
            calories = 330,
            protein = 12,
            carbs = 46,
            fats = 8,
            diet = "Vegan",
            goals = listOf("Weight Loss", "Balanced", "Healthy Living"),
            imageUrl = "white_chana",
            ingredients = listOf(
                "Kabuli white chickpeas",
                "Special chole spice mix",
                "Tomatoes, onions, and green chilies",
                "Fresh ginger juliennes"
            ),
            instructions = listOf(
                "Boil soaked white chickpeas with a tea bag for deep color.",
                "Sauté onions, tomatoes, and chole spices in oil.",
                "Simmer the chickpeas with gravy until thick, garnishing with green chilies."
            )
        ),
        CatalogMeal(
            id = "dal_chana_with_kaddu",
            title = "Dal Chana with Kaddu",
            category = "Dinner",
            calories = 290,
            protein = 11,
            carbs = 40,
            fats = 6,
            diet = "Vegan",
            goals = listOf("Weight Loss", "Healthy Living"),
            imageUrl = "dal_chana_with_kaddu",
            ingredients = listOf(
                "Split Bengal gram (Chana Dal)",
                "Bottle gourd (Kaddu/Lauki) cubes",
                "Onion, tomato, and garlic tarka"
            ),
            instructions = listOf(
                "Pressure-cook chana dal and bottle gourd cubes with salt and turmeric.",
                "Prepare a traditional tarka of sizzling garlic, onion, and tomatoes.",
                "Stir the cooked dal and kaddu into the hot tarka and simmer for 5 minutes."
            )
        ),
        CatalogMeal(
            id = "gajar_matar",
            title = "Gajar Matar Sabzi",
            category = "Dinner",
            calories = 240,
            protein = 5,
            carbs = 26,
            fats = 6,
            diet = "Vegan",
            goals = listOf("Weight Loss", "Healthy Living"),
            imageUrl = "gajar_matar",
            ingredients = listOf(
                "Fresh sweet red carrots (Gajar)",
                "Sweet green peas (Matar)",
                "Ginger, green chilies, and coriander seeds"
            ),
            instructions = listOf(
                "Toss chopped carrots and green peas in oil with crushed coriander seeds and ginger.",
                "Season with dry spices and stir-fry.",
                "Steam-cook on low flame until carrots are tender and sweet."
            )
        ),
        CatalogMeal(
            id = "band_gobi_matar",
            title = "Band Gobi Matar Sabzi",
            category = "Dinner",
            calories = 220,
            protein = 4,
            carbs = 22,
            fats = 6,
            diet = "Vegan",
            goals = listOf("Weight Loss", "Healthy Living"),
            imageUrl = "band_gobi_matar",
            ingredients = listOf(
                "Shredded green cabbage (Band Gobi)",
                "Green peas",
                "Mustard seeds, green chilies, and lemon juice"
            ),
            instructions = listOf(
                "Sauté mustard seeds, green chilies, and curry leaves.",
                "Add shredded cabbage and green peas with turmeric and salt.",
                "Sauté on medium-high heat until cooked but crunchy, splash with lemon juice."
            )
        ),
        CatalogMeal(
            id = "aloo_baingan",
            title = "Aloo Baingan Masala",
            category = "Dinner",
            calories = 310,
            protein = 5,
            carbs = 29,
            fats = 11,
            diet = "Vegan",
            goals = listOf("Balanced", "Healthy Living"),
            imageUrl = "aloo_baingan",
            ingredients = listOf(
                "Tender baby eggplants (Baingan)",
                "Potato cubes (Aloo)",
                "Spiced onion-tomato dry masala",
                "Amchur and garam masala"
            ),
            instructions = listOf(
                "Sauté sliced baby eggplants and potato cubes until slightly golden.",
                "Fold in sautéed onion-tomato dry masala with heavy spices.",
                "Slow-cook covered on low flame until tender and coated."
            )
        ),
        CatalogMeal(
            id = "chicken_curry",
            title = "Tariwala Chicken Curry",
            category = "Dinner",
            calories = 450,
            protein = 34,
            carbs = 11,
            fats = 22,
            diet = "Halal",
            goals = listOf("Muscle Gain", "Balanced"),
            imageUrl = "chicken_curry",
            ingredients = listOf(
                "Halal Chicken drumsticks/thighs",
                "Classic thin spiced curry broth (tari)",
                "Whole garam spices & coriander"
            ),
            instructions = listOf(
                "Sauté whole spices and finely pureed onions in oil until brown.",
                "Cook chicken pieces until seared, then add tomato purée.",
                "Pour in hot water and simmer slowly to create a flavorful thin red curry."
            )
        )
    )

    val vegWeeklyMenu = listOf(
        DayMenu("Monday", listOf("Matar Paneer", "Sabzi", "Salad", "Achaar", "10 Rotis")),
        DayMenu("Tuesday", listOf("Mix Vegetable", "Black Chana", "Salad", "Achaar", "10 Rotis")),
        DayMenu("Wednesday", listOf("Rajma", "Curry Pakora", "Jeera Rice", "Salad", "Achaar", "Sweet Dish")),
        DayMenu("Thursday", listOf("Shahi Tarka Dal", "Aloo Gobi", "Salad", "Achaar", "10 Rotis")),
        DayMenu("Friday", listOf("Palak Paneer", "Shahi Nutri Korma", "Salad", "Achaar", "Sweet Dish", "Cold Drink"), special = true),
        DayMenu("Saturday", listOf("Dal Chana with Kaddu", "Gajar Matar", "Salad", "Achaar", "10 Rotis")),
        DayMenu("Sunday", listOf("White Chana", "Band Gobi Matar", "Salad", "Achaar", "10 Rotis"))
    )

    val nonVegWeeklyMenu = listOf(
        DayMenu("Monday", listOf("Chicken Korma", "Aloo Baingan", "Salad", "Achaar", "10 Rotis")),
        DayMenu("Tuesday", listOf("Chicken Aloo Matar", "Mix Vegetable", "Salad", "Achaar", "10 Rotis")),
        DayMenu("Wednesday", listOf("Rajma", "Curry Pakora", "Jeera Rice", "Salad", "Achaar", "Sweet Dish")),
        DayMenu("Thursday", listOf("Mutton Korma", "Shahi Tarka Dal", "Salad", "Achaar", "10 Rotis")),
        DayMenu("Friday", listOf("Butter Chicken", "Palak Paneer", "Salad", "Achaar", "Sweet Dish", "Cold Drink"), special = true),
        DayMenu("Saturday", listOf("Chicken Curry", "Dal Chana with Kaddu", "Salad", "Achaar", "10 Rotis")),
        DayMenu("Sunday", listOf("White Chana", "Band Gobi Matar", "Salad", "Achaar", "10 Rotis"))
    )
}

data class DayMenu(
    val day: String,
    val dishes: List<String>,
    val special: Boolean = false
)
