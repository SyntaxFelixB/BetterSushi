package de.syntax_institut.bettersushi.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(
    navController: NavController
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            selected = currentDestination == Screen.Dishes.route,
            onClick = {
                navController.navigate(Screen.Dishes.route)
            },
            icon = {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Products"
                )
            }
        )

        NavigationBarItem(
            selected = currentDestination == Screen.CostOverview.route, //and here
            onClick = {
                navController.navigate(Screen.CostOverview.route)
            },
            icon = {
                Icon(
                    Icons.Filled.ShoppingCart,
                    contentDescription = "Cart"
                )
            }
        )
    }
}


