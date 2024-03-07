package de.syntax_institut.bettersushi.data.model

data class Restaurant(
    val name: String,
    val dishes: List<Dish>,
    val tables: List<String>,
    val categories: List<Category>
)
