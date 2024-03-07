package de.syntax_institut.bettersushi.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.syntax_institut.bettersushi.domain.CostOverviewViewModel
import de.syntax_institut.bettersushi.domain.DishViewModel
import de.syntax_institut.bettersushi.ui.components.NormalText
import de.syntax_institut.bettersushi.ui.screens.CostOverviewScreen
import de.syntax_institut.bettersushi.ui.screens.DishOverviewScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val dishViewModel: DishViewModel = viewModel()
    val costOverviewViewModel: CostOverviewViewModel = viewModel()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                navItems.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                                  navController.navigate(navItem.route) {
                                      popUpTo(navController.graph.findStartDestination().id) {
                                          saveState = true
                                      }
                                      launchSingleTop = true
                                      restoreState = true
                                  }
                        },
                        icon = {
                            Icon(
                               imageVector = navItem.icon,
                               contentDescription = null
                           )
                        },
                        label = {
                            NormalText(
                                text = navItem.label
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.DishOverviewScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.DishOverviewScreen.name) {
                DishOverviewScreen(
                    costOverviewViewModel,
                    dishViewModel
                )
            }
            composable(Screens.CostOverviewScreen.name) {
                CostOverviewScreen(
                    costOverviewViewModel
                )
            }
        }
    }
}