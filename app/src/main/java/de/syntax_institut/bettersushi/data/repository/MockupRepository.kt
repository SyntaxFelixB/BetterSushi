package de.syntax_institut.bettersushi.data.repository

import de.syntax_institut.bettersushi.R
import de.syntax_institut.bettersushi.data.model.Category
import de.syntax_institut.bettersushi.data.model.Dish

class MockupRepository {
    fun getDishes(): List<Dish> {
        return listOf(
            Dish("Lachs Maki", Category.Sushi, 3.99, R.drawable.sushi_sake),
            Dish("Thunfisch Maki", Category.Sushi, 2.99, R.drawable.sushi_tuna),
            Dish("Kappa Maki", Category.Sushi, 2.49, R.drawable.sushi_kappa),
            Dish("Tamago Maki", Category.Sushi, 2.99, R.drawable.sushi_tamago),
            Dish("Reis", Category.Sidedish, 1.99, R.drawable.extra_rice),
            Dish("Wasabi", Category.Sidedish, 0.49, R.drawable.extra_wasabi),
            Dish("Cola", Category.Drinks, 2.39, R.drawable.drink_cola),
            Dish("Limo", Category.Drinks, 2.49, R.drawable.drink_limo),
            Dish("Kaffee", Category.Drinks, 1.49, R.drawable.drink_coffe),
            Dish("Donut", Category.Deserts, 0.99, R.drawable.desert_donut),
            Dish("Muffin", Category.Deserts, 0.99, R.drawable.desert_muffin),
        )
    }

    fun getCategories(): List<Category> {
        return Category.entries
    }

    fun getTables(): List<String> {
        return listOf(
            "ABCD",
            "1234"
        )
    }
}