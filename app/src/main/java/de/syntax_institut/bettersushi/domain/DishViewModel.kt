package de.syntax_institut.bettersushi.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import de.syntax_institut.bettersushi.data.model.Category
import de.syntax_institut.bettersushi.data.model.Dish
import de.syntax_institut.bettersushi.data.repository.MockupRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DishViewModel : ViewModel() {

    private val mockupRepository = MockupRepository()

    private val _categories = MutableStateFlow(mockupRepository.getCategories())
    val categories: StateFlow<List<Category>>
        get() = _categories

    private val _dishes = MutableStateFlow(mockupRepository.getDishes())
    val dishes: StateFlow<List<Dish>>
        get() = _dishes

    private val _cart = MutableStateFlow<Map<Dish, Int>>(emptyMap())
    val cart: StateFlow<Map<Dish, Int>>
        get() = _cart

    private val _currentCategory = MutableStateFlow<Category?>(null)

    init {
        setCurrentCategory(_categories.value.first())
    }

    fun clearCart(): Map<Dish, Int> {
        val returnMap = _cart.value
        _cart.value = emptyMap()
        return returnMap
    }

    fun getCartValue(): Double {
        return _cart.value.map { entry ->
            entry.key.price * entry.value
        }.sum()
    }

    fun setCurrentCategory(category: Category) {
        _currentCategory.value = category
        _dishes.value = mockupRepository.getDishes().filter {
                dish -> dish.category == category
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
}