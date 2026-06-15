package uk.adbsalam.portfolio.shared.features.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState

@Composable
internal fun HomeHeader(
    headerHeight: Dp,
    parallaxTranslation: Float,
    onVideoClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    val hazeState = rememberHazeState()
    val blur = (parallaxTranslation / 20).coerceAtLeast(0f).coerceAtMost(20f)
    Box(modifier = Modifier) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
                    .blur(blur.dp)
                    .graphicsLayer { translationY = parallaxTranslation },
        ) {
            Profile(
                modifier =
                    Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .hazeSource(hazeState),
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(all = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                HeaderIcon(
                    hazeState = hazeState,
                    icon = Icons.Default.Share,
                    title = "Share app",
                    onClick = {},
                )

                HeaderIcon(
                    hazeState = hazeState,
                    icon = Icons.Default.Code,
                    title = "See Git",
                    onClick = {},
                )
                HeaderIcon(
                    hazeState = hazeState,
                    icon = Icons.Default.PlayArrow,
                    "Videos",
                    onClick = onVideoClick,
                )
                HeaderIcon(
                    hazeState = hazeState,
                    icon = Icons.Default.Settings,
                    "Settings",
                    onClick = onSettingsClick,
                )
            }
        }
    }
}
