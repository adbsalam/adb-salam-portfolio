package uk.adbsalam.portfolio.shared.features.home.components.card

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
internal fun ExpandableText(
    readMore: MutableState<Boolean>,
    text: String,
) {
    Column(
        modifier =
            Modifier
                .wrapContentSize()
                .animateContentSize(animationSpec = tween(500))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) { readMore.value = !readMore.value },
    ) {
        if (readMore.value) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        } else {
            Text(
                text = text,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
