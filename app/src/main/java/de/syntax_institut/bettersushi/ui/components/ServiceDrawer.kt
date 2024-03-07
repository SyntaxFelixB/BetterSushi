package de.syntax_institut.bettersushi.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.syntax_institut.bettersushi.ui.screens.DishOverviewPreview
import de.syntax_institut.bettersushi.ui.theme.BetterSushiTheme

@Composable
fun ServiceDrawer(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        gesturesEnabled = true,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                modifier = Modifier.width(266.dp)
            ) {
                DrawerSheet()
            }
        },
        content = content,
    )
}

@Composable
fun DrawerSheet() {
    Column (
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        TitleText(
            text = "Service",
            Modifier.padding(top = 45.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp, vertical = 20.dp)
        ) {
            NormalText(text = "Allgemeine Frage")
        }
        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp, vertical = 20.dp)
        ) {
            NormalText(text = "Service Rufen")
        }
        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp, vertical = 20.dp)
        ) {
            NormalText(text = "Beschwerde")
        }
        Spacer(modifier = Modifier.weight(1f))
        NormalText(
            text = "Sushi Paradise",
            Modifier.padding(bottom = 45.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    BetterSushiTheme {
        ServiceDrawer(
            content = {
                DishOverviewPreview()
            },
            drawerState = drawerState
        )
    }
}