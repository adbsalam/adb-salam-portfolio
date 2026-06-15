package uk.adbsalam.portfolio.shared.navigation.mainAppNav

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.metadata
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import com.adb.salam.clubmanager.shared.ui.design.navbar.FloatingAppBar
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import org.koin.compose.viewmodel.koinViewModel
import uk.adbsalam.portfolio.shared.core.config.Machine
import uk.adbsalam.portfolio.shared.core.config.currentMachine
import uk.adbsalam.portfolio.shared.features.home.HomeScreen
import uk.adbsalam.portfolio.shared.features.info.InfoScreen
import uk.adbsalam.portfolio.shared.navigation.navigate
import uk.adbsalam.portfolio.shared.navigation.popToRoot
import uk.adbsalam.portfolio.shared.ui.design.AppNavBar

@Composable
internal fun MainNavGraph(
    modifier: Modifier,
    viewModel: MainNavigationViewModel = koinViewModel(),
    onNavigateToThemeSelectionScreen: () -> Unit,
    onNavigateToNextScreen: () -> Unit,
) {
    val hazeState = rememberHazeState()
    val machine = currentMachine()
    val navBarState by viewModel.navBarState.collectAsState()
    val backStack: MutableList<MainAppNav> =
        rememberSerializable(serializer = SnapshotStateListSerializer()) {
            mutableStateListOf(
                MainAppNav.Home,
            )
        }

    LaunchedEffect(navBarState) {
        when (navBarState.selectedIndex) {
            MainAppNav.Home.index -> backStack.popToRoot()
            MainAppNav.ComponentLabs.index -> backStack.navigate(MainAppNav.ComponentLabs)
            MainAppNav.Info.index -> backStack.navigate(MainAppNav.Info)
            MainAppNav.Videos.index -> backStack.navigate(MainAppNav.Videos)
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        NavDisplay(
            modifier = Modifier.fillMaxSize().hazeSource(hazeState),
            backStack = backStack,
            transitionSpec = {
                val fromTab = initialState.key.toString().toMainNavEntry()
                val toTab = targetState.key.toString().toMainNavEntry()
                createTabTransitionSpec(fromTab, toTab)
            },
            predictivePopTransitionSpec = {
                fadeIn() togetherWith fadeOut()
            },
            popTransitionSpec = {
                val fromTab = initialState.key.toString().toMainNavEntry()
                val toTab = targetState.key.toString().toMainNavEntry()
                createTabTransitionSpec(fromTab, toTab)
            },
            onBack = { viewModel.onBackPress() },
            entryProvider =
                entryProvider {
                    entry<MainAppNav.Home>(
                        metadata =
                            metadata {
                            },
                    ) {
                        HomeScreen(onNavigateToSettingsScreen = onNavigateToThemeSelectionScreen)
                    }
                    entry<MainAppNav.Info> {
                        InfoScreen()
                    }
                    entry<MainAppNav.Videos> {
                        VideosScreen(onNavigateToNextScreen)
                    }
                    entry<MainAppNav.ComponentLabs> {
                        ComponentLabs(onNavigateToNextScreen)
                    }
                },
        )

        if (machine != Machine.IOS) {
            FloatingAppBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                navSelectionBackgroundOffset = navBarState.offset,
                hazeState = hazeState,
                onNavClicked = viewModel::onIndexNavChanged,
            )
        } else {
            AppNavBar(
                currentIndex = navBarState.selectedIndex,
                onTabSelected = { viewModel.onIndexNavChanged(0.dp, it) }, // ios is using native bar, no offset needed
            )
        }
    }
}

private fun String.toMainNavEntry(): MainAppNav? =
    when (this) {
        MainAppNav.Home::class.simpleName -> MainAppNav.Home
        MainAppNav.Info::class.simpleName -> MainAppNav.Info
        MainAppNav.Videos::class.simpleName -> MainAppNav.Videos
        MainAppNav.ComponentLabs::class.simpleName -> MainAppNav.ComponentLabs
        else -> MainAppNav.Home
    }

@Composable
private fun VideosScreen(onNavigateToNextScreen: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Videos Screen")
        Button(
            onClick = onNavigateToNextScreen,
        ) {
            Text("Go to Next")
        }
    }
}

@Composable
private fun ComponentLabs(onNavigateToNextScreen: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Component Labs")
        Button(
            onClick = onNavigateToNextScreen,
        ) {
            Text("Go to Next")
        }
    }
}
