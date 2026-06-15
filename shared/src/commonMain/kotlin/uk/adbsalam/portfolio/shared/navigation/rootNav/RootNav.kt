package uk.adbsalam.portfolio.shared.navigation.rootNav

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import uk.adbsalam.portfolio.shared.navigation.NavigationIntent
import uk.adbsalam.portfolio.shared.navigation.mainAppNav.MainNavGraph
import uk.adbsalam.portfolio.shared.navigation.navigateTo
import uk.adbsalam.portfolio.shared.ui.design.ElevatedScreenBackground
import uk.adbsalam.portfolio.theming.LocalNavigationSettings
import uk.adbsalam.portfolio.theming.ThemeSelection

@Composable
internal fun RootNav(onIntent: (NavigationIntent) -> Unit) {
    var mainNavVisible by rememberSaveable { mutableStateOf(true) }
    val navigationSettings = LocalNavigationSettings.current
    val animateMax = if (navigationSettings.alphaEnabled) navigationSettings.alphaAmount else 0f
    val blurMax = if (navigationSettings.blurEnabled) navigationSettings.blurAmount.dp else 0.dp
    val backStack: MutableList<RootNavRoute> =
        rememberSerializable(serializer = SnapshotStateListSerializer()) {
            mutableStateListOf(RootNavRoute.MainContent)
        }

    LaunchedEffect(backStack.size) {
        mainNavVisible = backStack.size == 1
    }

    val animateBlack =
        animateFloatAsState(
            targetValue = if (mainNavVisible) 0f else animateMax,
            animationSpec = tween(600),
        )

    val animateBlur =
        animateDpAsState(
            targetValue = if (mainNavVisible) 0.dp else blurMax,
            animationSpec = tween(600),
        )

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider =
            entryProvider {
                entry<RootNavRoute.MainContent>(
                    metadata = EntrypointNavMetaData,
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        MainNavGraph(
                            modifier = Modifier.blur(animateBlur.value),
                            onNavigateToThemeSelectionScreen = { backStack.navigateTo(RootNavRoute.ThemeSelection) },
                            onNavigateToNextScreen = { backStack.navigateTo(RootNavRoute.Infographics) },
                        )
                        Box(
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .alpha(animateBlack.value)
                                    .background(
                                        Color.Black,
                                    ),
                        )
                    }
                }
                entry<RootNavRoute.ThemeSelection>(metadata = NavigationMetaData) {
                    ElevatedScreenBackground {
                        ThemeSelection(
                            modifier = Modifier,
                            onTheme = {
                                onIntent(NavigationIntent.OnThemeChanged(it))
                            },
                            onAnimationPrefChange = {
                                onIntent(NavigationIntent.OnAnimationPrefChange(it))
                            },
                            onNavigationSettingsChange = {
                                onIntent(NavigationIntent.OnNavigationPrefChange(it))
                            },
                            onDone = { backStack.removeLastOrNull() },
                        )
                    }
                }
                entry<RootNavRoute.Infographics>(metadata = NavigationMetaData) {
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        shape = RectangleShape,
                        elevation = CardDefaults.cardElevation(100.dp),
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text("Infographic")
                            Button({
                                if (backStack.size > 1) {
                                    backStack.removeLastOrNull()
                                }
                            }) {
                                Text("Back")
                            }
                        }
                    }
                }
            },
    )
}
