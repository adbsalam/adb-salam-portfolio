package uk.adbsalam.portfolio.shared.navigation

import com.adb.salam.clubmanager.shared.ui.theming.ThemeType
import uk.adbsalam.portfolio.theming.NavigationSettings

sealed interface NavigationIntent {
    data class OnThemeChanged(
        val themeType: ThemeType,
    ) : NavigationIntent

    data class OnAnimationPrefChange(
        val isSelected: Boolean,
    ) : NavigationIntent

    data class OnNavigationPrefChange(
        val navigationSettings: NavigationSettings,
    ) : NavigationIntent
}
