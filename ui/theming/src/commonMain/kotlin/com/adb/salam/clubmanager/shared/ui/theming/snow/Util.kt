package com.adb.salam.clubmanager.shared.ui.theming.snow

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import kotlin.random.Random

internal const val snowflakeDensity = 0.004
internal val incrementRange = 0.4f..0.8f
internal val sizeRange = 5.0f..12.0f
internal const val angleSeed = 25.0f
internal const val angleRange = 0.1f
internal const val angleDivisor = 10000.0f
internal val angleSeedRange = -angleSeed..angleSeed

fun ClosedRange<Float>.random(): Float = Random.nextFloat() * (endInclusive - start) + start

fun Float.random(): Float = Random.nextFloat() * this

fun Int.random(): Int = Random.nextInt(this)

fun IntSize.randomPosition() = Offset(width.random().toFloat(), height.random().toFloat())
