package uk.adbsalam.portfolio.shared.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import uk.adbsalam.portfolio.home.feature.components.card.LottiInfoCard
import uk.adbsalam.portfolio.shared.features.home.components.HomeHeader
import uk.adbsalam.portfolio.shared.features.home.components.card.InfoCard
import uk.adbsalam.portfolio.shared.features.home.components.data.HomeItemType
import uk.adbsalam.portfolio.shared.features.home.components.data.HomeScreenItem
import uk.adbsalam.portfolio.shared.features.home.components.data.homeItemsList

@Composable
fun HomeScreen(onNavigateToSettingsScreen: () -> Unit) {
    val scrollState = rememberScrollState()
    val headerHeight = LocalWindowInfo.current.containerDpSize.height.value / 1.75
    val hazeState = rememberHazeState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier =
                Modifier
                    .verticalScroll(scrollState)
                    .hazeSource(hazeState)
                    .fillMaxSize(),
        ) {
            HomeHeader(
                headerHeight = headerHeight.dp,
                parallaxTranslation = 0.5f * scrollState.value,
                onVideoClick = {},
                onSettingsClick = onNavigateToSettingsScreen,
            )

            Column(
                modifier = Modifier.padding(vertical = 12.dp).zIndex(10f),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                homeItemsList.forEach { item ->
                    InfoCardByType(
                        item = item,
                        animateLottie = !scrollState.isScrollInProgress,
                    ) {
                    }
                }
            }

            Spacer(modifier = Modifier.height(300.dp))
        }
    }
}

@Composable
private fun InfoCardByType(
    item: HomeScreenItem,
    animateLottie: Boolean,
    action: () -> Unit,
) {
    when (item.type) {
        HomeItemType.IMAGE_CARD ->
            InfoCard(
                tags = item.tags,
                title = item.title,
                body = item.body,
                drawable = getDrawableRes(item.res),
                action = action,
            )

        HomeItemType.LOTTI_CARD -> {
            LottiInfoCard(
                tags = item.tags,
                title = item.title,
                body = item.body,
                name = item.res,
                animate = animateLottie,
                action = action,
            )
        }

        HomeItemType.LOTTI_SINGLE -> {
            LottiInfoCard(
                tags = item.tags,
                title = item.title,
                body = item.body,
                name = item.res,
                animate = animateLottie,
                action = action,
            )
        }
    }
}
