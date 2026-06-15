package uk.adbsalam.portfolio.shared.features.home.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.adb.salam.clubmanager.shared.ui.theming.appCardRoundedBackground
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun InfoCard(
    tags: List<String>,
    title: String,
    body: String,
    drawable: DrawableResource,
    action: () -> Unit,
) {
    val readMore = remember { mutableStateOf(false) }

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .appCardRoundedBackground(),
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
        )

        CardInfoText(
            tags = tags,
            title = title,
            body = body,
            readMore = readMore,
            action = action,
        )
    }
}
