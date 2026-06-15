package uk.adbsalam.portfolio.shared.features.info.components.infocards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adb.salam.clubmanager.shared.ui.theming.appCardRoundedBackground
import portfolio.features.info.generated.resources.Res
import portfolio.features.info.generated.resources.ic_compose_logo
import portfolio.features.info.generated.resources.ic_firebase
import portfolio.features.info.generated.resources.ic_git
import portfolio.features.info.generated.resources.ic_kotlin
import uk.adbsalam.portfolio.shared.features.info.components.charts.CircularChart

/**
 * Top level chart to show Circular Chart
 * Animated progress will be shown
 */
@Composable
internal fun AndroidMainCard() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .weight(1f)
                        .appCardRoundedBackground()
                        .padding(vertical = 10.dp),
            ) {
                CircularChart(
                    modifier = Modifier.fillMaxWidth(),
                    title = "KMP",
                    subTitle = "Kotlin",
                    drawable = Res.drawable.ic_kotlin,
                    percent = 1f,
                )
            }

            Column(
                modifier =
                    Modifier
                        .weight(1f)
                        .appCardRoundedBackground()
                        .padding(vertical = 10.dp),
            ) {
                CircularChart(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Design",
                    subTitle = "Compose",
                    drawable = Res.drawable.ic_compose_logo,
                    percent = 0.9f,
                )
            }
        }

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .weight(1f)
                        .appCardRoundedBackground()
                        .padding(vertical = 10.dp),
            ) {
                CircularChart(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Firebase",
                    subTitle = "Cloud",
                    drawable = Res.drawable.ic_firebase,
                    percent = 0.7f,
                )
            }

            Column(
                modifier =
                    Modifier
                        .weight(1f)
                        .appCardRoundedBackground()
                        .padding(vertical = 10.dp),
            ) {
                CircularChart(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Version Control",
                    subTitle = "Git",
                    drawable = Res.drawable.ic_git,
                    percent = 0.8f,
                )
            }
        }
    }
}
