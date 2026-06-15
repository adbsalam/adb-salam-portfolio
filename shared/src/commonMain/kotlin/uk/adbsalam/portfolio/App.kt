package uk.adbsalam.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.adb.salam.clubmanager.shared.ui.theming.AppTheme
import com.adb.salam.clubmanager.shared.ui.theming.LocalAnimationPrefProvider
import com.adb.salam.clubmanager.shared.ui.theming.LocalThemeProvider
import com.adb.salam.clubmanager.shared.ui.theming.ThemeType
import org.koin.compose.viewmodel.koinViewModel
import uk.adbsalam.portfolio.shared.navigation.NavigationIntent
import uk.adbsalam.portfolio.shared.navigation.rootNav.RootNav
import uk.adbsalam.portfolio.theming.LocalNavigationSettings
import uk.adbsalam.portfolio.theming.ThemeState
import uk.adbsalam.portfolio.theming.ThemeViewModel
import uk.adbsalam.portfolio.theming.appbackground.snow.snowfall

@Composable
fun App(themeViewModel: ThemeViewModel = koinViewModel()) {
    val themeState by themeViewModel.themeState.collectAsState()
    LaunchedEffect(null) {
        themeViewModel.loadCurrentTheme()
    }

    when (themeState) {
        ThemeState.Loading -> {
            AppTheme(
                themeType = ThemeType.SYSTEM,
            ) {
                Box(Modifier.fillMaxSize())
            }
        }

        is ThemeState.Loaded -> {
            val state = (themeState as ThemeState.Loaded)
            CompositionLocalProvider(
                LocalThemeProvider provides state.themeType,
                LocalAnimationPrefProvider provides state.animationPref,
                LocalNavigationSettings provides state.navigationSettings,
            ) {
                val snowModifier = if (state.themeType == ThemeType.CHRISTMAS) Modifier.snowfall() else Modifier
                AppTheme(themeType = state.themeType) {
                    Box(
                        modifier =
                            Modifier
                                .then(snowModifier)
                                .background(MaterialTheme.colorScheme.surface)
                                .fillMaxSize(),
                    ) {
                        RootNav { intent ->
                            when (intent) {
                                is NavigationIntent.OnThemeChanged -> {
                                    themeViewModel.saveAppTheme(intent.themeType)
                                }

                                is NavigationIntent.OnAnimationPrefChange -> {
                                    themeViewModel.saveAnimationPref(intent.isSelected)
                                }

                                is NavigationIntent.OnNavigationPrefChange -> {
                                    themeViewModel.saveNavigationSettings(intent.navigationSettings)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
