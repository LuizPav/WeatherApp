package com.example.weatherapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Orange40,
    secondary = Orange80,
    tertiary = OrangeLight,
    background = BackgroundDark,
    surface = DarkGreySurface,
    onPrimary = BackgroundDark,
    onSecondary = BackgroundDark,
    onBackground = OnBackground,
    onSurface = OnBackground,
)

private val LightColorScheme = lightColorScheme(
    primary = Orange40,
    secondary = Orange80,
    tertiary = OrangeLight,
    background = DarkGrey40,
    surface = DarkGreySurface,
    onPrimary = OnBackground,
    onSecondary = BackgroundDark,
    onBackground = OnBackground,
    onSurface = OnBackground,
)

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}