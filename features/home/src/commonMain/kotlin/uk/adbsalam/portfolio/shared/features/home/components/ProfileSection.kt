package uk.adbsalam.portfolio.shared.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.adb.salam.clubmanager.shared.ui.theming.LocalThemeProvider
import com.adb.salam.clubmanager.shared.ui.theming.ThemeType
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import dev.chrisbanes.haze.rememberHazeState
import org.jetbrains.compose.resources.painterResource
import portfolio.features.home.generated.resources.Res
import portfolio.features.home.generated.resources.ic_garden_dark
import portfolio.features.home.generated.resources.ic_profile

/**
 * Hard coded component since will not change anytime soon
 */
@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
internal fun Profile(modifier: Modifier = Modifier) {
    val hazeState = rememberHazeState()
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        val theme = LocalThemeProvider.current
        val isSystemLight = theme == ThemeType.SYSTEM && !isSystemInDarkTheme()
        val image =
            if (theme == ThemeType.LIGHT || isSystemLight) Res.drawable.ic_garden_dark else Res.drawable.ic_garden_dark
        Image(
            modifier = Modifier.fillMaxSize().hazeSource(hazeState),
            painter = painterResource(image),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painterResource(Res.drawable.ic_profile),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .size(150.dp)
                        .clip(CircleShape),
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier =
                    Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .hazeEffect(
                            state = hazeState,
                            style = HazeMaterials.ultraThin(),
                        ).padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Abdul Salam",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "@adb_salam",
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}
