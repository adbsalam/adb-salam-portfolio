package uk.adbsalam.portfolio.shared.ui.design

import androidx.compose.runtime.Composable

@Composable
expect fun Switch(
    isSelected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
)
