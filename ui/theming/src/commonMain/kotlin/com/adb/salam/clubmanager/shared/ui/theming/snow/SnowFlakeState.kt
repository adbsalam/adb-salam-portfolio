package uk.adbsalam.portfolio.theming.appbackground.snow

import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.unit.IntSize
import com.adb.salam.clubmanager.shared.ui.theming.snow.angleRange
import com.adb.salam.clubmanager.shared.ui.theming.snow.angleSeed
import com.adb.salam.clubmanager.shared.ui.theming.snow.incrementRange
import com.adb.salam.clubmanager.shared.ui.theming.snow.random
import com.adb.salam.clubmanager.shared.ui.theming.snow.randomPosition
import com.adb.salam.clubmanager.shared.ui.theming.snow.sizeRange
import com.adb.salam.clubmanager.shared.ui.theming.snow.snowflakeDensity
import kotlin.math.PI
import kotlin.math.roundToInt

internal data class SnowflakesState(
    var tickNanos: Long,
    val snowflakes: List<Snowflake>,
) {
    constructor(tick: Long, canvasSize: IntSize) : this(tick, createSnowflakes(canvasSize))

    fun draw(canvas: Canvas) {
        snowflakes.forEach { it.draw(canvas) }
    }

    fun resize(newSize: IntSize) = copy(snowflakes = createSnowflakes(newSize))

    companion object {
        private fun createSnowflakes(canvasSize: IntSize): List<Snowflake> {
            val canvasArea = canvasSize.width * canvasSize.height
            val normalizedDensity = snowflakeDensity.coerceIn(0.0..1.0) / 500.0
            val snowflakesCount = (canvasArea * normalizedDensity).roundToInt()
            return List(snowflakesCount) {
                Snowflake(
                    incrementFactor = incrementRange.random(),
                    size = sizeRange.random(),
                    canvasSize = canvasSize,
                    position = canvasSize.randomPosition(),
                    angle = angleSeed.random() / angleSeed * angleRange + (PI / 2.0) - (angleRange / 2.0),
                )
            }
        }
    }
}
