package com.adb.salam.clubmanager.shared.ui.theming

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

enum class DeviceDisplayMode {
    PhonePortrait,
    PhoneLandscape,
    TablePortrait,
    TabletLandscape,
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun rememberDeviceDisplayMode(): DeviceDisplayMode {
    val containerSize = LocalWindowInfo.current.containerSize
    val widthDp = containerSize.width.dp
    val heightDp = containerSize.height.dp
    val dpSize = DpSize(widthDp, heightDp)

    val windowSizeClass = WindowSizeClass.calculateFromSize(dpSize)
    val isLandscapeRatio = widthDp >= heightDp

    return when {
        windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact -> {
            if (isLandscapeRatio) {
                DeviceDisplayMode.TabletLandscape
            } else {
                DeviceDisplayMode.TablePortrait
            }
        }
        else -> {
            if (isLandscapeRatio) {
                DeviceDisplayMode.PhoneLandscape
            } else {
                DeviceDisplayMode.PhonePortrait
            }
        }
    }
}
