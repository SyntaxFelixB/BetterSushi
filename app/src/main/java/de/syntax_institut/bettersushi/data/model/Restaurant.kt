package de.syntax_institut.bettersushi.data.model

// Cut for simplicity
data class Restaurant(
    val name: String,
    val dishes: List<Dish>,
    val tables: List<String>,
    val categories: List<Category>
)
