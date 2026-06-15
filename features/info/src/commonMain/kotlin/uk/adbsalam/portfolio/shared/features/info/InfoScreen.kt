package uk.adbsalam.portfolio.shared.features.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adb.salam.clubmanager.shared.ui.theming.appCardRoundedBackground
import uk.adbsalam.portfolio.shared.features.info.components.WorkInfo
import uk.adbsalam.portfolio.shared.features.info.components.infocards.AndroidMainCard
import uk.adbsalam.portfolio.shared.features.info.components.infocards.SkillsInsightCard
import uk.adbsalam.portfolio.shared.features.info.data.InfographicsData
import uk.adbsalam.portfolio.shared.features.info.data.WorkHistory

/**
 * Main Info screen upon successfully loading data
 * This is the main Info Screen to show
 */
@Composable
fun InfoScreen(
    infographics: InfographicsData = InfographicsData.data,
    workHistory: WorkHistory = WorkHistory.data,
) {
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
    ) {
        Text(
            modifier = Modifier.statusBarsPadding(),
            text = "Primary Android Skills",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        AndroidMainCard()

        Text(
            text = "Have a look at my skill set",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        SkillsInsightCard(infographics = infographics)

        Text(
            text = "My Work History",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .appCardRoundedBackground()
                    .padding(vertical = 20.dp),
        ) {
            workHistory.workHistory.forEachIndexed { index, item ->
                WorkInfo(
                    showDivider = index != workHistory.workHistory.lastIndex,
                    workHistory = item,
                )
            }
        }

        Spacer(modifier = Modifier.height(120.dp))
    }
}
