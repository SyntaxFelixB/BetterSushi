package de.syntax_institut.bettersushi.data.repository

import de.syntax_institut.bettersushi.R
import de.syntax_institut.bettersushi.data.model.Category
import de.syntax_institut.bettersushi.data.model.Dish
import de.syntax_institut.bettersushi.data.model.Restaurant

class MockupRepository : IRepository {
    override fun getDishes(): List<Dish> {
        return listOf(
            Dish("Lachs Maki", Category.Sushi, 3.99, R.drawable.sushi_sake),
            Dish("Thunfisch Maki", Category.Sushi, 2.99, R.drawable.sushi_tuna),
            Dish("Kappa Maki", Category.Sushi, 2.49, R.drawable.sushi_kappa),
            Dish("Tamago Maki", Category.Sushi, 2.99, R.drawable.sushi_tamago),
            Dish("Reis", Category.Sidedish, 1.99, R.drawable.extra_rice),
            Dish("Wasabi", Category.Sidedish, 0.49, R.drawable.extra_wasabi),
            Dish("Cola", Category.Drink, 2.39, R.drawable.drink_cola),
            Dish("Limo", Category.Drink, 2.49, R.drawable.drink_limo),
            Dish("Kaffee", Category.Drink, 1.49, R.drawable.drink_coffe),
            Dish("Donut", Category.Desert, 0.99, R.drawable.desert_donut),
            Dish("Muffin", Category.Desert, 0.99, R.drawable.desert_muffin),
        )
    }

    override fun getRestaurants(): List<Restaurant> {
        return listOf(
            Restaurant(
                "Sushiplace",
                getDishes(),
                listOf(
                    "ABCD",
                    "1234"
                ),
                Category.entries
            )
        )
    }
}