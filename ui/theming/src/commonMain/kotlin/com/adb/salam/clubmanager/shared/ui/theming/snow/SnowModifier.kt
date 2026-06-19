package uk.adbsalam.portfolio.theming.appbackground.snow

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.TimeSource

/**
 * Modifier to enable snow fall on a compose
 */
fun Modifier.snowfall() =
    composed {
        var snowflakesState by remember {
            mutableStateOf(SnowflakesState(-1, IntSize(0, 0)))
        }
        val scope = rememberCoroutineScope()
        val haptic = LocalHapticFeedback.current



        LaunchedEffect(Unit) {
            while (isActive) {
                withFrameNanos { newTick ->
                    val elapsedMillis =
                        (newTick - snowflakesState.tickNanos).nanoseconds.inWholeMilliseconds
                    val wasFirstRun = snowflakesState.tickNanos < 0
                    snowflakesState.tickNanos = newTick
                    if (wasFirstRun) return@withFrameNanos
                    for (snowflake in snowflakesState.snowflakes) {
                        snowflake.update(elapsedMillis)
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            val random = Random(800)
            val endTime = TimeSource.Monotonic.markNow() + 1.minutes

            while (isActive && TimeSource.Monotonic.markNow() < endTime) {
                delay(random.nextLong(300, 800).milliseconds)
                haptic.performHapticFeedback(HapticFeedbackType.SegmentFrequentTick)
            }
        }

        onSizeChanged { newSize -> snowflakesState = snowflakesState.resize(newSize) }
            .clipToBounds()
            .drawWithContent {
                drawContent()
                snowflakesState.draw(drawContext.canvas)
            }
    }
