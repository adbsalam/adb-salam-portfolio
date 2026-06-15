package uk.adbsalam.portfolio.shared.features.info.components.infocards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adb.salam.clubmanager.shared.ui.theming.appCardRoundedBackground
import uk.adbsalam.portfolio.shared.features.info.components.InfoTitle
import uk.adbsalam.portfolio.shared.features.info.components.charts.HorizontalChart
import uk.adbsalam.portfolio.shared.features.info.data.InfographicsData

@Composable
internal fun SkillsInsightCard(infographics: InfographicsData) {
    var expandInfoBars by remember { mutableStateOf(false) }

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .appCardRoundedBackground()
                .padding(14.dp),
    ) {
        InfoTitle(
            title = "Skills Insight",
        )

        infographics.infoGraphics.subList(0, 5).forEach {
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalChart(
                subTitle = it.name,
                percent = it.value,
            )
        }

        AnimatedVisibility(
            visible = expandInfoBars,
        ) {
            Column(Modifier.fillMaxWidth()) {
                infographics.infoGraphics.subList(5, infographics.infoGraphics.size).forEach {
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalChart(
                        subTitle = it.name,
                        percent = it.value,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = { expandInfoBars = !expandInfoBars },
        ) {
            Text(text = if (expandInfoBars) "see less" else "see more")
        }
    }
}
