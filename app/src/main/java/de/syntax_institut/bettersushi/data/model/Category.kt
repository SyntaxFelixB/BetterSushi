package de.syntax_institut.bettersushi.data.model

import de.syntax_institut.bettersushi.R

enum class Category(val value: Int) {
    Sushi(R.drawable.cat_sushi),
    Sidedish(R.drawable.cat_side),
    Drink(R.drawable.cat_drink),
    Desert(R.drawable.cat_desert)
}