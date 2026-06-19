package uk.adbsalam.portfolio.shared.navigation.mainAppNav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity
import uk.adbsalam.portfolio.shared.core.config.NestedScrollDirection

fun Modifier.nestedScrollDirection(
    onScrollDirectionChange: (NestedScrollDirection) -> Unit,
): Modifier {
    val nestedScroll = object : NestedScrollConnection {
        override fun onPreScroll(
            available: Offset,
            source: NestedScrollSource
        ): Offset {
            when {
                available.y > 10 -> onScrollDirectionChange(NestedScrollDirection.Down)
                available.y < -10 -> onScrollDirectionChange(NestedScrollDirection.Up)
            }
            return Offset.Zero
        }

        override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
            onScrollDirectionChange(NestedScrollDirection.NONE)
            return super.onPostFling(consumed, available)
        }
    }

    return this.nestedScroll(nestedScroll)
}