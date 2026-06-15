package uk.adbsalam.portfolio.shared.ui.design

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun ElevatedScreenBackground(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(100.dp),
    ) {
        content()
    }
}
