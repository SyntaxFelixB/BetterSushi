package de.syntax_institut.bettersushi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import de.syntax_institut.bettersushi.R
import de.syntax_institut.bettersushi.domain.RestaurantViewModel
import de.syntax_institut.bettersushi.ui.components.NormalText
import de.syntax_institut.bettersushi.ui.components.TitleText
import de.syntax_institut.bettersushi.ui.navigation.Screens
import de.syntax_institut.bettersushi.ui.theme.BetterSushiTheme

@Composable
fun LandingScreen(
    navController: NavHostController = rememberNavController(),
    restaurantViewModel: RestaurantViewModel
) {
    val maxLength = 4
    var text by remember { mutableStateOf("") }
    val tableFound by restaurantViewModel.foundTable.collectAsState()


    LaunchedEffect(tableFound) {
        if (tableFound) {
            navController.navigate(Screens.DishOverviewScreen.name)
        }
    }

    Scaffold { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.landing_bg),
                contentDescription = "Background",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .blur(2.dp)
                    .matchParentSize()
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.weight(5f))
            Row(
                Modifier
                    .background(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(bottom = 12.dp, start = 25.dp, end = 25.dp, top = 12.dp)
            ) {
                TitleText(text = "Sushi Paradise")
            }
            Spacer(Modifier.weight(1f))
            TextField(
                value = text,
                onValueChange = {
                    if (it.length <= maxLength) {
                        text = it
                    }
                },
                singleLine = true,
                shape = MaterialTheme.shapes.small,
                placeholder = {
                    Text("TischID")
                }
            )
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    restaurantViewModel.selectTable(text)
                },
                enabled = text.length == maxLength
            ) {
                NormalText("Tisch aussuchen")
            }
            Spacer(Modifier.weight(5f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    BetterSushiTheme {
        LandingScreen(rememberNavController(), viewModel())
    }
}