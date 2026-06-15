package uk.adbsalam.portfolio.shared.ui.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnimationSlider(
    amount: Float,
    modifier: Modifier = Modifier,
    range: ClosedFloatingPointRange<Float>,
    steps: Int,
    title: String = "",
    onValueChange: (Float) -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            style = MaterialTheme.typography.bodySmall,
            text = "$title: ${amount.toInt()}",
        )

        Slider(
            value = amount,
            onValueChange = { onValueChange(it) },
            valueRange = range,
            steps = steps,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}
