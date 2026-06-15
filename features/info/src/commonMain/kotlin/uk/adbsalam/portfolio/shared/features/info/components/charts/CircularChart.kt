package uk.adbsalam.portfolio.shared.features.info.components.charts

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/**
 * @param modifier Modifier to apply on chart
 * @param title title of card to add
 * @param subTitle subtitle of card to be used
 * @param icon icon drawable resource for current chart
 * @param percent max value to reach on this progressbar with animated float
 *
 * This will create an animated circular chart for given values
 */
@Composable
internal fun CircularChart(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    drawable: DrawableResource,
    percent: Float,
) {
    var percentFloat by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(key1 = null) {
        percentFloat = percent
    }

    val progressAnimation by animateFloatAsState(
        targetValue = percentFloat,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = "",
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                strokeWidth = 12.dp,
                progress = { progressAnimation },
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )

            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                modifier =
                    Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = subTitle,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}
