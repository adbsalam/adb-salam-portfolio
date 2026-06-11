package com.adb.salam.clubmanager.shared.ui.theming

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

val roundedContainerShape: RoundedCornerShape = RoundedCornerShape(20.dp)

fun Modifier.appCardRoundedBackground() =
    composed {
        this
            .background(color = MaterialTheme.colorScheme.background, roundedContainerShape)
            .clip(roundedContainerShape)
    }
