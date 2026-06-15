package uk.adbsalam.portfolio.shared.ui.design

import androidx.compose.runtime.Composable

@Composable
expect fun AppNavBar(
    currentIndex: Int,
    onTabSelected: (Int) -> Unit,
)
