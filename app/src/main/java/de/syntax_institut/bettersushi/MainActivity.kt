package de.syntax_institut.bettersushi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.bettersushi.domain.CostOverviewViewModel
import de.syntax_institut.bettersushi.domain.DishViewModel
import de.syntax_institut.bettersushi.domain.RestaurantViewModel
import de.syntax_institut.bettersushi.ui.navigation.AppNavigation
import de.syntax_institut.bettersushi.ui.screens.LandingScreen
import de.syntax_institut.bettersushi.ui.theme.BetterSushiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BetterSushiTheme {
                val restaurantViewModel: RestaurantViewModel = viewModel()
                val restaurantFound = restaurantViewModel.foundRestaurant.collectAsState()
                val tableFound = restaurantViewModel.foundTable.collectAsState()

                val dishViewModel: DishViewModel = viewModel()
                val costOverviewViewModel: CostOverviewViewModel = viewModel()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.tertiary
                ) {
                    if(restaurantFound.value != null && tableFound.value) {
                        restaurantFound.value?.let { restaurant ->
                            dishViewModel.setRestaurant(restaurant)
                            AppNavigation(
                                dishViewModel,
                                costOverviewViewModel
                            )
                        }
                    } else {
                        LandingScreen(
                            restaurantViewModel = restaurantViewModel,
                        )
                    }
                }
            }
        }
    }
}