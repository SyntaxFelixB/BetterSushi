package de.syntax_institut.bettersushi.data.repository

import de.syntax_institut.bettersushi.data.model.Dish
import de.syntax_institut.bettersushi.data.model.Restaurant

interface IRepository {
    fun getDishes(): List<Dish>
    fun getRestaurants(): List<Restaurant>
}