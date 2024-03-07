package de.syntax_institut.bettersushi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import de.syntax_institut.bettersushi.ui.components.DishRowItem
import de.syntax_institut.bettersushi.ui.components.NormalText
import de.syntax_institut.bettersushi.ui.components.QuantitySelector
import de.syntax_institut.bettersushi.ui.components.TitleText
import de.syntax_institut.bettersushi.ui.theme.BetterSushiTheme
import de.syntax_institut.bettersushi.util.formatPrice
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostOverviewScreen(
    navController: NavController,
    costOverviewViewModel: CostOverviewViewModel
) {
    val dishes by costOverviewViewModel.orderedDishes.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

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
            Modifier.fillMaxSize()
        ) {
            LazyColumn {
                items(dishes.keys.toList()) { dish ->
                    DishRowItem(
                        dish = dish,
                    ) {
                        Row(
                            Modifier.width(100.dp)
                        ) {
                            NormalText("x${dishes[dish]}")
                            Spacer(Modifier.weight(1f))
                            NormalText(formatPrice(dishes[dish]!! * dish.price))
                        }
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    showBottomSheet = true
                },
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {
                NormalText(text = "${formatPrice(costOverviewViewModel.getTotalPrice())} Bezahlen")
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.onSecondary
            ) {
                // Sheet content
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TitleText(
                        text = "Bezahlen",
                        Modifier.padding(bottom = 20.dp)
                    )
                    Button(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        },
                        Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                    ) {
                        NormalText(text = "Bar")
                    }
                    Button(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        },
                        Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                    ) {
                        NormalText(text = "Karte")
                    }
                    Button(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        },
                        enabled = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 60.dp, start = 20.dp, end = 20.dp)
                    ) {
                        NormalText(text = "Paypal")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CostOverviewPreview() {
    BetterSushiTheme {
        CostOverviewScreen(rememberNavController(), viewModel())
    }
}