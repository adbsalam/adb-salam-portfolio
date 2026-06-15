package uk.adbsalam.portfolio.shared.navigation.rootNav

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.metadata
import androidx.navigation3.ui.NavDisplay

val EntrypointNavMetaData =
    metadata {
        put(NavDisplay.TransitionKey) {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(600),
            ) togetherWith
                slideOutHorizontally(
                    targetOffsetX = { -it / 4 },
                    animationSpec = tween(600),
                )
        }
        put(NavDisplay.PopTransitionKey) {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(600),
            ) togetherWith
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(600),
                )
        }
        put(NavDisplay.PredictivePopTransitionKey) {
            slideInHorizontally(
                animationSpec = tween(600),
                initialOffsetX = { -it / 4 },
            ) togetherWith
                slideOutHorizontally(
                    animationSpec = tween(600),
                    targetOffsetX = { it },
                )
        }
    }

val NavigationMetaData =
    metadata {
        put(NavDisplay.TransitionKey) {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(600),
            ) togetherWith
                slideOutHorizontally(
                    targetOffsetX = { -it / 4 },
                    animationSpec = tween(600),
                ) // + scaleOut(targetScale = 0.9f) <- see if it looks better
        }
        put(NavDisplay.PopTransitionKey) {
//        scaleIn(
//            initialScale = 0.9f,
//            animationSpec = tween(400)
//        ) + // see if needed
            slideInHorizontally(
                initialOffsetX = { -it / 4 },
                animationSpec = tween(600),
            ) togetherWith
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(600),
                )
        }
        put(NavDisplay.PredictivePopTransitionKey) {
            slideInHorizontally(
                animationSpec = tween(600),
                initialOffsetX = { -it / 4 },
            ) togetherWith
                slideOutHorizontally(
                    animationSpec = tween(600),
                    targetOffsetX = { it },
                )
        }
    }
