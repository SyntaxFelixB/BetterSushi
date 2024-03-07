package de.syntax_institut.bettersushi.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.syntax_institut.bettersushi.domain.CostOverviewViewModel
import de.syntax_institut.bettersushi.domain.DishViewModel
import de.syntax_institut.bettersushi.domain.RestaurantViewModel
import de.syntax_institut.bettersushi.ui.screens.CostOverviewScreen
import de.syntax_institut.bettersushi.ui.screens.DishOverviewScreen
import de.syntax_institut.bettersushi.ui.screens.LandingScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val restaurantViewModel: RestaurantViewModel = viewModel()
    val dishViewModel: DishViewModel = viewModel()
    val costOverviewViewModel: CostOverviewViewModel = viewModel()
    val tableFound by restaurantViewModel.foundTable.collectAsState()

    Scaffold(
        bottomBar = {
            if(tableFound) {
                BottomNavBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Landing.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Landing.route) {
                LandingScreen(
                    navController,
                    restaurantViewModel,
                    dishViewModel
                )
            }
            composable(Screen.Dishes.route) {
                DishOverviewScreen(
                    navController,
                    costOverviewViewModel,
                    dishViewModel
                )
            }
            composable(Screen.CostOverview.route) {
                CostOverviewScreen(
                    navController,
                    costOverviewViewModel
                )
            }
        }
    }
}

sealed class Screen(val route: String) {
    data object Landing : Screen("Landing")
    data object Dishes : Screen("Dishes")
    data object CostOverview : Screen("Pay")
}
