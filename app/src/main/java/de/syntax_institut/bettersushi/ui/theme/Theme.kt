package de.syntax_institut.bettersushi.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = SI_DeepPurple,
    secondary = SI_Yellow,
    tertiary = SI_Purple,
    onPrimary = Color.White,
    onSecondary = SI_Grey,
    onTertiary = SI_Green
)

private val LightColorScheme = lightColorScheme(
    primary = SI_DeepPurple,
    secondary = SI_Yellow,
    tertiary = SI_Purple,
    onPrimary = Color.White,
    onSecondary = SI_Grey,
    onTertiary = SI_Green
)

@Composable
fun BetterSushiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}