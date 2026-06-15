package uk.adbsalam.portfolio.shared.navigation.mainAppNav
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.ui.unit.IntOffset

fun createTabTransitionSpec(
    initialKeys: MainAppNav?,
    targetKeys: MainAppNav?,
): ContentTransform {
    // Fallback animation if routes aren't tabs
    if (initialKeys == null || targetKeys == null) {
        return fadeIn(tween(600)) togetherWith fadeOut(tween(600))
    }

    // FIX: Moving right means target index is GREATER than initial index
    // Going from 0 (Home) -> 1 (Search) means movingRight = true
    val movingRight = targetKeys.index > initialKeys.index

    // If moving right: incoming screen comes from right (width), outgoing exits to left (-width)
    // If moving left: incoming screen comes from left (-width), outgoing exits to right (width)
    val enterOffset =
        if (movingRight) {
            { width: Int -> width }
        } else {
            { width: Int -> -width }
        }
    val exitOffset =
        if (movingRight) {
            { width: Int -> -width }
        } else {
            { width: Int -> width }
        }

    val animSpec = tween<IntOffset>(durationMillis = 350, easing = LinearOutSlowInEasing)
    val fadeSpec = tween<Float>(durationMillis = 200)

    return (slideInHorizontally(animationSpec = animSpec, initialOffsetX = enterOffset) + fadeIn(fadeSpec)) togetherWith
        (slideOutHorizontally(animationSpec = animSpec, targetOffsetX = exitOffset) + fadeOut(fadeSpec))
}
