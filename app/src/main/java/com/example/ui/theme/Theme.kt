package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = LightEmerald,
    secondary = LeafGreen,
    tertiary = AccentOrange,
    background = DarkGrayBackground,
    surface = DarkGraySurface,
    onPrimary = DarkGrayBackground,
    onSecondary = DarkGrayBackground,
    onBackground = MintCream,
    onSurface = MintCream
  )

private val LightColorScheme =
  lightColorScheme(
    primary = FreshGreen,
    secondary = SoftLime,
    tertiary = AccentOrange,
    background = CreamBackground,
    surface = androidx.compose.ui.graphics.Color.White, // White from compose graphics
    onPrimary = androidx.compose.ui.graphics.Color.White,
    onSecondary = androidx.compose.ui.graphics.Color.White,
    onBackground = DarkGrayBackground,
    onSurface = DarkGrayBackground
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Disable dynamic color to enforce our beautiful cohesive branding
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  // Let's import explicit Color if needed, wait, let's make sure Color is imported correctly
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
