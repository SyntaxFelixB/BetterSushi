package de.syntax_institut.bettersushi.domain

import androidx.lifecycle.ViewModel
import de.syntax_institut.bettersushi.data.repository.MockupRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RestaurantViewModel : ViewModel() {
    private val mockupRepository = MockupRepository()

    private val _foundTable = MutableStateFlow(false)
    val foundTable: StateFlow<Boolean>
        get() = _foundTable

    private val _tables = MutableStateFlow(mockupRepository.getTables())

    fun selectTable(tableID: String) {
        if(_tables.value.contains(tableID)) {
            _foundTable.value = true
        }
    }
}