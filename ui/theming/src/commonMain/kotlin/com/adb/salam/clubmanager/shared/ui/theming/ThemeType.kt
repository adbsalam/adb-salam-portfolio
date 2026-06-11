package com.adb.salam.clubmanager.shared.ui.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

enum class ThemeType(val title: String, ) {
    SYSTEM(title = "System Default"),
    LIGHT(title = "Light"),
    DARK(title = "Dark"),
    DEEP_DARK(title = "Deep Dark"),
    CHRISTMAS(title = "Christmas (Seasonal)"),
}
