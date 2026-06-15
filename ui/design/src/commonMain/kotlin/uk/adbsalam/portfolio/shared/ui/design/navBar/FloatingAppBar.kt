package com.adb.salam.clubmanager.shared.ui.design.navbar

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adb.salam.clubmanager.shared.ui.theming.LocalThemeProvider
import com.adb.salam.clubmanager.shared.ui.theming.ThemeType
import com.adb.salam.clubmanager.shared.ui.theming.christmas_background_color
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import portfolio.ui.design.generated.resources.Res
import portfolio.ui.design.generated.resources.experiment

@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun FloatingAppBar(
    modifier: Modifier,
    navSelectionBackgroundOffset: Dp,
    hazeState: HazeState,
    onNavClicked: (Dp, Int) -> Unit,
) {
    val density = LocalDensity.current
    var homeOffset by remember { mutableStateOf(0.dp) }
    var pollsOffset by remember { mutableStateOf(0f.dp) }
    var shoutsOffset by remember { mutableStateOf(0f.dp) }
    var accountsOffset by remember { mutableStateOf(0f.dp) }
    val indicatorColor =
        if (LocalThemeProvider.current == ThemeType.CHRISTMAS) christmas_background_color else MaterialTheme.colorScheme.secondaryContainer
    val animateBackground =
        animateDpAsState(
            targetValue = navSelectionBackgroundOffset,
            animationSpec =
                spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow,
                ),
        )

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp, vertical = 16.dp)
                .navigationBarsPadding()
                .clip(RoundedCornerShape(50.dp))
                .hazeEffect(
                    hazeState,
                    style = HazeMaterials.thick(
                        MaterialTheme.colorScheme.surfaceContainer
                    )
                )
    ) {
        Box(
            modifier =
                Modifier
                    .padding(vertical = 6.dp)
                    .clickable(
                        onClick = {},
                        indication = null,
                        interactionSource = null,
                    ),
        ) {
            Box(
                modifier =
                    Modifier
                        .size(width = 42.dp, height = 24.dp)
                        .offset(x = animateBackground.value, y = 4.dp)
                        .background(indicatorColor, shape = RoundedCornerShape(30.dp)),
            )
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                AppNavBarItem(
                    modifier =
                        Modifier.onGloballyPositioned {
                            homeOffset = with(density) { it.positionInRoot().x.toDp() }
                        },
                    label = "Home",
                    icon = Icons.Rounded.Home,
                    onClick = {
                        onNavClicked(homeOffset, 0)
                    },
                )

                AppNavBarItem(
                    modifier =
                        Modifier.onGloballyPositioned {
                            shoutsOffset = with(density) { it.positionInRoot().x.toDp() }
                        },
                    label = "Labs",
                    drawable = Res.drawable.experiment,
                    onClick = {
                        onNavClicked(shoutsOffset, 1)
                    },
                )

                AppNavBarItem(
                    modifier =
                        Modifier.onGloballyPositioned {
                            pollsOffset = with(density) { it.positionInRoot().x.toDp() }
                        },
                    label = "Info",
                    icon = Icons.Rounded.Info,
                    onClick = {
                        onNavClicked(pollsOffset, 2)
                    },
                )

                AppNavBarItem(
                    modifier =
                        Modifier.onGloballyPositioned {
                            accountsOffset = with(density) { it.positionInRoot().x.toDp() }
                        },
                    label = "Videos",
                    icon = Icons.Rounded.PlayArrow,
                    onClick = {
                        onNavClicked(accountsOffset, 3)
                    },
                )
            }
        }
    }
}
