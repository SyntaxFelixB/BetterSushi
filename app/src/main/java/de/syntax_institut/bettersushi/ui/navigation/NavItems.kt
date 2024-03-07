package de.syntax_institut.bettersushi.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val navItems = listOf(
    NavItem(
        label = "Menu",
        icon = Icons.Default.Menu,
        route = Screens.DishOverviewScreen.name
    ),
    NavItem(
        label = "Bestellungen",
        icon = Icons.Default.ShoppingCart,
        route = Screens.CostOverviewScreen.name
    )
)