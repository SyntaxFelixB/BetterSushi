package de.syntax_institut.bettersushi.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import de.syntax_institut.bettersushi.data.model.Category
import de.syntax_institut.bettersushi.data.model.Dish
import de.syntax_institut.bettersushi.data.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DishViewModel : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>>
        get() = _categories

    private val _dishes = MutableStateFlow<List<Dish>>(emptyList())
    val dishes: StateFlow<List<Dish>>
        get() = _dishes

    private val _cart = MutableStateFlow<Map<Dish, Int>>(emptyMap())
    val cart: StateFlow<Map<Dish, Int>>
        get() = _cart

    private val _currentCategory = MutableStateFlow<Category?>(null)
    private val _restaurant = MutableStateFlow<Restaurant?>(null)

    fun setRestaurant(restaurant: Restaurant) {
        _restaurant.value = restaurant
        setCategories()
    }

    fun clearCart(): Map<Dish, Int> {
        val returnMap = _cart.value
        _cart.value = emptyMap()
        return returnMap
    }

    fun setCurrentCategory(category: Category) {
        _currentCategory.value = category
        _restaurant.value?.let { restaurant ->
            _dishes.value = restaurant.dishes.filter {
                    dish -> dish.category == category
            }
        }
    }

    fun orderDish(dish: Dish) {
        val updatedMap = _cart.value.toMutableMap().apply {
            this[dish] = (this[dish]?: 0) + 1  // Update quantity or create a new entry
        }
        _cart.value = updatedMap
        Log.d("Cart", _cart.value.toString())
    }

    fun unOrderDish(dish: Dish) {
        val updatedMap = _cart.value.toMutableMap().apply {
            this[dish] = (this[dish]?: 0) - 1
            this[dish]?.let {
                if(it <= 0) {
                    this.remove(dish)
                }
            }
        }
        _cart.value = updatedMap
        Log.d("Cart", _cart.value.toString())
    }

    private fun setCategories() {
        _restaurant.value?.let { restaurant ->
            _categories.value = restaurant.categories
            setCurrentCategory(restaurant.categories.first())
            _dishes.value = restaurant.dishes.filter {
                    dish -> dish.category == restaurant.categories.first()
            }
        }
    }
}