package de.syntax_institut.bettersushi.domain

import androidx.lifecycle.ViewModel
import de.syntax_institut.bettersushi.data.model.Category
import de.syntax_institut.bettersushi.data.model.Dish
import de.syntax_institut.bettersushi.data.model.Restaurant
import de.syntax_institut.bettersushi.data.repository.MockupRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RestaurantViewModel : ViewModel() {
    private val repository = MockupRepository()

    private val _restaurants = MutableStateFlow<List<Restaurant>>(repository.getRestaurants())
    val restaurants: StateFlow<List<Restaurant>>
        get() = _restaurants

    private val _foundTable = MutableStateFlow<Boolean>(false)
    val foundTable: StateFlow<Boolean>
        get() = _foundTable

    private val _foundRestaurant = MutableStateFlow<Restaurant?>(null)
    private val _tables = MutableStateFlow<List<String>>(emptyList())

    // TODO: Create selection and remove
    init {
        selectRestaurant("Sushiplace")
    }

    fun selectRestaurant(restaurantName: String) {
        _restaurants.value.find { restaurant ->
            restaurantName == restaurant.name
        }?.let { restaurant ->
            _foundRestaurant.value = restaurant
            _tables.value = restaurant.tables.toMutableList()
        }
    }

    fun selectTable(tableID: String, setRestaurant: (Restaurant) -> Unit, ) {
        if(_tables.value.contains(tableID)) {
            _foundTable.value = true
            _foundRestaurant.value?.let { restaurant ->
                setRestaurant(restaurant)
            }
        }
    }
}