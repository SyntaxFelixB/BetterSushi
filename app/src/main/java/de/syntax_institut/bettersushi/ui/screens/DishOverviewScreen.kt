package de.syntax_institut.bettersushi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import de.syntax_institut.bettersushi.R
import de.syntax_institut.bettersushi.domain.CostOverviewViewModel
import de.syntax_institut.bettersushi.domain.DishViewModel
import de.syntax_institut.bettersushi.ui.components.DishRowItem
import de.syntax_institut.bettersushi.ui.components.NormalText
import de.syntax_institut.bettersushi.ui.components.QuantitySelector

@Composable
fun DishOverviewScreen(
    navController: NavController,
    costOverviewViewModel: CostOverviewViewModel,
    dishViewModel: DishViewModel,
) {
    val categories by dishViewModel.categories.collectAsState()
    val dishes by dishViewModel.dishes.collectAsState()
    val cart by dishViewModel.cart.collectAsState()
    val scrollState = rememberLazyListState()

    Scaffold { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.dish_bg),
                contentDescription = "Background",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .blur(2.dp)
                    .matchParentSize()
            )
        }
        Column(
            Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                LazyRow(
                    state = scrollState,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    itemsIndexed(categories) { index, image ->
                        Card(
                            modifier = Modifier.height(100.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 8.dp
                            )
                        ) {
                            Image(
                                painterResource(id = image.value),
                                contentDescription = "Image",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.width(100.dp)
                            )
                        }
                    }
                }
            }
            LazyColumn {
                items(dishes) { dish ->
                    DishRowItem(
                        dish = dish,
                    ) {
                        QuantitySelector(
                            count = cart[dish] ?: 0,
                            decreaseCount = { dishViewModel.unOrderDish(dish) },
                            increaseCount = { dishViewModel.orderDish(dish) }
                        )
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    costOverviewViewModel.addOrder(dishViewModel.clearCart())
                },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {
                NormalText(text = "Bestellen")
            }
        }
    }
}

@Preview
@Composable
fun DishOverviewPreview() {
    DishOverviewScreen(rememberNavController(), viewModel(), viewModel())
}