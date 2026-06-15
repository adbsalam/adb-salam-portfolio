package uk.adbsalam.portfolio.shared.features.info.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.WorkHistory
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import uk.adbsalam.portfolio.shared.features.info.data.WorkHistory
import uk.adbsalam.portfolio.shared.features.info.util.workIcon

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun WorkInfo(
    showDivider: Boolean,
    workHistory: WorkHistory.Work,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .animateContentSize(),
    ) {
        var expand by remember { mutableStateOf(false) }
        var chevronRotation by remember { mutableStateOf(0f) }

        val chevronRotate by animateFloatAsState(
            targetValue = chevronRotation,
            animationSpec = tween(durationMillis = 300, easing = LinearEasing),
            label = "",
        )

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, bottom = 8.dp, end = 10.dp)
                    .clickable {
                        chevronRotation = if (!expand) 90f else 0f
                        expand = !expand
                    },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.WorkHistory,
                    contentDescription = null,
                    tint = workTint(workHistory.color),
                )

                Text(
                    text = workHistory.company,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = workHistory.duration,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.rotate(chevronRotate),
            )
        }

        AnimatedVisibility(visible = expand) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
            ) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    workHistory.tags.forEach {
                        AssistChip(
                            label = { Text(text = it.tag) },
                            leadingIcon = {
                                Icon(
                                    modifier = Modifier.sizeIn(maxHeight = 30.dp, maxWidth = 30.dp),
                                    painter = painterResource(workIcon(it.icon)),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                )
                            },
                            onClick = {},
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = workHistory.description,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        if (!showDivider) return@Column

        HorizontalDivider(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
        )
    }
}

private fun workTint(color: String): Color =
    when (color) {
        "orange" -> Color(0xFFFF9800)
        "purple" -> Color(0xFF9C27B0)
        "pink" -> Color(0xFFFF4785)
        "green" -> Color(0xFF4CAF50)
        else -> Color.Unspecified
    }
