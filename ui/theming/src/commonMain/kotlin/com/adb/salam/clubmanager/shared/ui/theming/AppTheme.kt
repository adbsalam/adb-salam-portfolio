package com.adb.salam.clubmanager.shared.ui.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val DarkColorScheme =
    darkColorScheme(
        primary = primary_dark,
        secondary = secondary_dark,
        tertiary = tertiary_dark,
        background = dark_background,
        onPrimary = Color.White,
        surface = dark_surface,
        secondaryContainer = primary_dark,
        surfaceVariant = dark_surface_variant,
        inverseSurface = Color.Black,
    )

internal val LightColorScheme =
    lightColorScheme(
        primary = primary_light,
        secondary = secondary_light,
        tertiary = tertiary_light,
        background = light_backgraound,
        secondaryContainer = secondary_container_light,
        surface = light_surface,
        surfaceVariant = light_surface_variant,
        inverseSurface = Color.White,
    )

internal val Christmas =
    lightColorScheme(
        primary = Color.Black,
        secondary = Color.White,
        tertiary = Color.White,
        background = Color(0xFFF08A8A),
        secondaryContainer = Color.White,
        inverseSurface = Color.Red,
    )

internal val DeepDark =
    darkColorScheme(
        primary = DeepDarkColorScheme.primary_deep_dark,
        secondary = DeepDarkColorScheme.secondary_deep_dark,
        tertiary = tertiary_dark,
        background = DeepDarkColorScheme.background_deep_dark,
        onPrimary = DeepDarkColorScheme.onBackground_deep_dark,
        surface = DeepDarkColorScheme.surface_deep_dark,
        secondaryContainer = primary_dark,
        surfaceVariant = dark_surface_variant,
        inverseSurface = Color.Black,
    )

/**
 *
 */
@Composable
fun AppTheme(
    isSystemDark: Boolean = isSystemInDarkTheme(),
    themeType: ThemeType = ThemeType.SYSTEM,
    content: @Composable () -> Unit,
) {

    val colorScheme =  when (themeType) {
        ThemeType.CHRISTMAS -> Christmas
        ThemeType.DEEP_DARK -> DeepDark
        ThemeType.LIGHT -> LightColorScheme
        ThemeType.DARK -> DarkColorScheme
        ThemeType.SYSTEM ->
            if (isSystemDark) DeepDark else LightColorScheme
    }

    val appFont = AppFontFamily()

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
        typography =
            Typography(
                displayLarge =
                    MaterialTheme.typography.displayLarge.copy(
                        fontFamily = appFont,
                        color = colorScheme.onBackground,
                    ),
                displayMedium =
                    MaterialTheme.typography.displayMedium.copy(
                        fontFamily = appFont,
                        color = colorScheme.onBackground,
                    ),
                displaySmall =
                    MaterialTheme.typography.displaySmall.copy(
                        fontFamily = appFont,
                        color = colorScheme.onBackground,
                    ),
                headlineLarge =
                    MaterialTheme.typography.headlineLarge.copy(
                        fontFamily = appFont,
                        color = colorScheme.onBackground,
                    ),
                headlineMedium =
                    MaterialTheme.typography.headlineMedium.copy(
                        fontFamily = appFont,
                        color = colorScheme.onBackground,
                    ),
                headlineSmall =
                    MaterialTheme.typography.headlineSmall.copy(
                        fontFamily = appFont,
                        color = colorScheme.onBackground,
                    ),
                titleLarge =
                    MaterialTheme.typography.titleLarge.copy(
                        fontFamily = appFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = colorScheme.onBackground,
                    ),
                titleMedium =
                    MaterialTheme.typography.titleMedium.copy(
                        fontFamily = appFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = colorScheme.onBackground,
                    ),
                titleSmall =
                    MaterialTheme.typography.titleSmall.copy(
                        fontFamily = appFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = colorScheme.onBackground,
                    ),
                bodyLarge =
                    MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = appFont,
                        color = colorScheme.onBackground,
                    ),
                bodyMedium =
                    MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = appFont,
                        color = colorScheme.onBackground,
                    ),
                bodySmall =
                    MaterialTheme.typography.bodySmall.copy(
                        fontFamily = appFont,
                        color = colorScheme.onBackground,
                    ),
                labelLarge =
                    MaterialTheme.typography.labelLarge.copy(
                        fontFamily = appFont,
                        color = colorScheme.onBackground,
                    ),
                labelMedium =
                    MaterialTheme.typography.labelMedium.copy(
                        fontFamily = appFont,
                        color = colorScheme.onBackground,
                    ),
                labelSmall =
                    MaterialTheme.typography.labelSmall.copy(
                        fontFamily = appFont,
                        color = colorScheme.onBackground,
                    ),
            ),
    )
}
