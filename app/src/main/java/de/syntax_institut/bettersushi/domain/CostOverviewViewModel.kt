package de.syntax_institut.bettersushi.domain

import androidx.lifecycle.ViewModel
import de.syntax_institut.bettersushi.data.model.Dish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CostOverviewViewModel : ViewModel() {
    private val _orderedDishes = MutableStateFlow<MutableMap<Dish, Int>>(mutableMapOf())
    val orderedDishes: StateFlow<Map<Dish, Int>>
        get() = _orderedDishes

    fun addOrder(order: Map<Dish, Int>) {
        order.forEach {
            (k, v) -> _orderedDishes.value.merge(k, v, Integer::sum)
        }
    }

    fun getTotalPrice(): Double {
        return _orderedDishes.value.map { entry ->
            entry.key.price * entry.value
        }.sum()
    }
}