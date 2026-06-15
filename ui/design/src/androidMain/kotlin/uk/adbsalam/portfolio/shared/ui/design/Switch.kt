package uk.adbsalam.portfolio.shared.ui.design

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
actual fun Switch(
    isSelected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
) {
    val colorScheme = MaterialTheme.colorScheme

    Switch(
        checked = isSelected,
        onCheckedChange = onSelectedChange,
        modifier = Modifier,
        colors =
            SwitchDefaults.colors(
                checkedTrackColor = colorScheme.primary,
                checkedThumbColor = colorScheme.onPrimary,
                uncheckedTrackColor = colorScheme.surfaceVariant,
                uncheckedThumbColor = colorScheme.outline,
                uncheckedBorderColor = Color.Transparent,
                disabledCheckedTrackColor = colorScheme.primary.copy(alpha = 0.12f),
                disabledUncheckedTrackColor = colorScheme.surfaceVariant.copy(alpha = 0.06f),
            ),
    )
}
