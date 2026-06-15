package uk.adbsalam.portfolio

import androidx.compose.runtime.Composable

sealed interface NavigationScreen {
    object Home : NavigationScreen

    object Info : NavigationScreen

    object Videos : NavigationScreen

    object Reviews : NavigationScreen
}

@Composable
fun NavSample() {
}
