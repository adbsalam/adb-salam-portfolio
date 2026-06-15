package com.adb.salam.clubmanager.shared.ui.theming

import androidx.compose.runtime.staticCompositionLocalOf

val LocalThemeProvider = staticCompositionLocalOf { ThemeType.SYSTEM }
val LocalAnimationPrefProvider = staticCompositionLocalOf { false }

enum class ThemeType(
    val title: String,
    val description: String = "",
) {
    SYSTEM(title = "System Default"),
    LIGHT(title = "Light"),
    Twilight(title = "Twilight"),
    Charcoal(title = "Charcoal"),
    DEEP_DARK(
        title = "Deep Dark",
        description = "For true draculas, a fully black out theme that do not bring in any light, beware this theme also hide away all the shadows",
    ),
    CHRISTMAS(
        title = "Christmas (Seasonal)",
        description = "A seasonal special theme to go with snowy christmas season",
    ),
}
