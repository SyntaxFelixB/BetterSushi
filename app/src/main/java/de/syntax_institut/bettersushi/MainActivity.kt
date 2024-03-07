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
import de.syntax_institut.bettersushi.domain.RestaurantViewModel
import de.syntax_institut.bettersushi.ui.components.ServiceDrawer
import de.syntax_institut.bettersushi.ui.navigation.AppNavigation
import de.syntax_institut.bettersushi.ui.screens.LandingScreen
import de.syntax_institut.bettersushi.ui.theme.BetterSushiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BetterSushiTheme {
                val restaurantViewModel: RestaurantViewModel = viewModel()
                val tableFound = restaurantViewModel.foundTable.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.tertiary
                ) {
                    if(tableFound.value) {
                        ServiceDrawer {
                            AppNavigation()
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