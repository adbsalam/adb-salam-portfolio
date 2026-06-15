package uk.adbsalam.portfolio.shared.ui.design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.uikit.LocalUIViewController
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.NSLayoutConstraint
import platform.UIKit.UIDevice
import platform.UIKit.UITabBar

fun isIOS26OrAbove(): Boolean {
    val versionString = UIDevice.currentDevice.systemVersion

    // Extracts the major version number safely (e.g., "26.1.3" -> 26)
    val majorVersion = versionString.split(".").firstOrNull()?.toIntOrNull() ?: 0

    return majorVersion >= 26
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun AppNavBar(
    currentIndex: Int,
    onTabSelected: (Int) -> Unit,
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val viewController = LocalUIViewController.current
    val iosOnItemSelected: (Int) -> Unit = { index ->
        onTabSelected(index)
    }
    var selectedIndex by remember { mutableStateOf(currentIndex) }
    val items =
        listOf(
            UIKitUITabBarItem(
                title = "Home",
                image = UIKitImage.SystemName("house.fill"),
            ),
            UIKitUITabBarItem(
                title = "Labs",
                image = UIKitImage.SystemName("flask.fill"),
            ),
            UIKitUITabBarItem(
                title = "Info",
                image = UIKitImage.SystemName("info.circle.fill"),
            ),
            UIKitUITabBarItem(
                title = "Videos",
                image = UIKitImage.SystemName("video.fill"),
            ),
        )

    // Write the measured tab bar height to the CompositionLocal so AdaptiveScaffold
    // can automatically adjust content padding on iOS.

    val tabBarView =
        remember {
            UITabBar().apply {
                translatesAutoresizingMaskIntoConstraints = false
            }
        }
    val isLiquidGlassEnabled = remember { isIOS26OrAbove() }

    val onItemSelectedState by rememberUpdatedState(iosOnItemSelected)

    val tabBarManager =
        remember(tabBarView) {
            TabBarManager(
                tabBar = tabBarView,
                onItemSelected = { onItemSelectedState(it) },
            )
        }

    LaunchedEffect(selectedIndex) {
        tabBarManager.setItems(items, selectedIndex, density.density)
    }

    val initialSafeAreaBottom =
        remember {
            if (isLiquidGlassEnabled) {
                0.dp
            } else {
                viewController.view.safeAreaInsets.useContents { bottom.dp }
            }
        }

    var topLeft by remember { mutableStateOf(DpOffset.Zero) }
    var positionInRoot by remember { mutableStateOf(DpOffset.Zero) }
    var tabBarWidth by remember { mutableStateOf(0.dp) }
    var tabBarHeight by remember {
        val tabBarHeight = initialSafeAreaBottom + 12.dp
        mutableStateOf(tabBarHeight)
    }

    DisposableEffect(tabBarView, viewController) {
        viewController.view.addSubview(tabBarView)

        NSLayoutConstraint.activateConstraints(
            listOf(
                tabBarView.leadingAnchor.constraintEqualToAnchor(viewController.view.leadingAnchor),
                tabBarView.trailingAnchor.constraintEqualToAnchor(viewController.view.trailingAnchor),
                tabBarView.bottomAnchor.constraintEqualToAnchor(
                    if (isLiquidGlassEnabled) {
                        viewController.view.bottomAnchor
                    } else {
                        viewController.view.safeAreaLayoutGuide.bottomAnchor
                    },
                ),
            ),
        )

        onDispose {
            tabBarView.removeFromSuperview()
        }
    }

    LaunchedEffect(Unit) {
        var tabBarHeightConsistencyCounter = 0

        while (true) {
            tabBarView.frame.useContents {
                topLeft =
                    DpOffset(
                        x = origin.x.dp,
                        y = origin.y.dp,
                    )
                tabBarWidth = size.width.dp
                val safeAreaBottom =
                    if (isLiquidGlassEnabled) {
                        0.dp
                    } else {
                        viewController.view.safeAreaInsets.useContents { bottom.dp }
                    }
                val newTabBarHeight = size.height.dp + safeAreaBottom

                if (tabBarHeight != newTabBarHeight) {
                    tabBarHeight = newTabBarHeight
                    tabBarHeightConsistencyCounter = 0
                } else {
                    tabBarHeightConsistencyCounter++
                }
            }

            if (tabBarHeight.value > 0f && tabBarHeightConsistencyCounter > 6) {
                break
            }

            withFrameMillis { }
        }
    }

    // Clean up the padding when this composable leaves the composition
    DisposableEffect(Unit) {
        onDispose {
            //  iosTabBarPaddingState.value = PaddingValues()
        }
    }

    Box(
        modifier =
            Modifier
                .onPlaced {
                    val positionInRootPx = it.positionInRoot()
                    positionInRoot =
                        with(density) {
                            DpOffset(
                                x = positionInRootPx.x.toDp(),
                                y = positionInRootPx.y.toDp(),
                            )
                        }
                }.graphicsLayer {
                    translationX = (topLeft.x - positionInRoot.x).toPx()
                    translationY = (topLeft.y - positionInRoot.y).toPx()
                }.width(tabBarWidth)
                .height(tabBarHeight),
    )
}
