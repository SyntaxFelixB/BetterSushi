package de.syntax_institut.bettersushi.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NormalText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color? = null
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = color ?: Color.Unspecified,
        modifier = modifier
    )
}