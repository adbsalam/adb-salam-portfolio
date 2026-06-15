package uk.adbsalam.portfolio.home.feature.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import com.adb.salam.clubmanager.shared.ui.theming.appCardRoundedBackground
import io.github.alexzhirkevich.compottie.*
import org.jetbrains.compose.resources.painterResource
import portfolio.features.home.generated.resources.Res
import portfolio.features.home.generated.resources.ic_garden_dark
import uk.adbsalam.portfolio.shared.features.home.components.card.CardInfoText

/**
 * @param tags tags list to show chips for
 * @param title title text to be set
 * @param body body or description of card component
 * @param resId resource ID to be used to set image
 * @param action action to perform on View button is clicked
 */
@Composable
internal fun LottiInfoCard(
    name: String,
    tags: List<String>,
    title: String,
    body: String,
    animate: Boolean,
    action: () -> Unit,
) {
    val readMore = remember { mutableStateOf(false) }
    val composition by rememberLottieComposition {
        val archiveBytes = Res.readBytes("files/$name.lottie")
        LottieCompositionSpec.DotLottie(archiveBytes)
    }
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .appCardRoundedBackground(),
    ) {
        if (LocalInspectionMode.current) {
            Image(
                painter = painterResource(Res.drawable.ic_garden_dark),
                contentDescription = null,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp),
            )
        } else {
            val progress by animateLottieCompositionAsState(
                composition = composition,
                isPlaying = false,
                restartOnPlay = false,
                iterations = 100,
            )

            Image(
                painter = rememberLottiePainter(composition, { progress }),
                contentDescription = "DotLottie Compressed Animation",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp),
            )
        }

        CardInfoText(
            tags = tags,
            title = title,
            body = body,
            readMore = readMore,
            action = action,
        )
    }
}
