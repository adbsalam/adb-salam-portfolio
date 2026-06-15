package uk.adbsalam.portfolio.shared.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials

@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
internal fun HeaderIcon(
    hazeState: HazeState,
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier =
                Modifier
                    .size(60.dp)
                    .padding(6.dp)
                    .clickable { onClick() }
                    .clip(RoundedCornerShape(50.dp))
                    .hazeEffect(
                        state = hazeState,
                        style = HazeMaterials.ultraThin(),
                    ),
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = icon,
                tint = Color.White,
                contentDescription = null,
            )
        }

        Text(text = title, color = Color.White, fontSize = 11.sp)
    }
}
