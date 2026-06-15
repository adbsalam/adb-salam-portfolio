package uk.adbsalam.portfolio.theming

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adb.salam.clubmanager.shared.ui.theming.LocalAnimationPrefProvider
import com.adb.salam.clubmanager.shared.ui.theming.LocalThemeProvider
import com.adb.salam.clubmanager.shared.ui.theming.ThemeType
import uk.adbsalam.portfolio.shared.ui.design.AnimationSlider
import uk.adbsalam.portfolio.shared.ui.design.RadioGroup
import uk.adbsalam.portfolio.shared.ui.design.Switch

@Composable
fun ThemeSelection(
    modifier: Modifier = Modifier,
    onTheme: (ThemeType) -> Unit,
    onAnimationPrefChange: (Boolean) -> Unit,
    onNavigationSettingsChange: (NavigationSettings) -> Unit,
    onDone: () -> Unit,
) {
    val currentTheme = LocalThemeProvider.current
    val currentAnimate = LocalAnimationPrefProvider.current
    val navigationSettings = LocalNavigationSettings.current
    var preSelectedTheme by remember { mutableStateOf(currentTheme) }
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .verticalScroll(rememberScrollState())
                .systemBarsPadding()
                .padding(all = 16.dp)
                .then(modifier), // haze modifier so before padding
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.titleMedium,
        )

        HorizontalDivider(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp),
        )
        RadioGroup(
            items = ThemeType.entries,
            title = { item -> item.title },
            preSelect = preSelectedTheme,
            onSelected = { item ->
                preSelectedTheme = item
                onTheme(item)
            },
        )

        Divider()

        SettingsSwitch(
            title = "Weather effects (Christmas only)",
            isSelected = currentAnimate,
            onSelectedChange = onAnimationPrefChange,
        )

        Divider()

        Text(
            text = "While navigating between screens, enable or disable blur animation of screens",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(Modifier.height(16.dp))

        SettingsSwitch(
            title = "Enable navigation blur",
            isSelected = navigationSettings.blurEnabled,
            onSelectedChange = {
                onNavigationSettingsChange(
                    navigationSettings.copy(blurEnabled = it),
                )
            },
        )

        Spacer(Modifier.height(16.dp))

        AnimationSlider(
            modifier = Modifier,
            range = 5f..40f,
            steps = 8,
            amount = navigationSettings.blurAmount,
            title = "Blur Amount",
            onValueChange = {
                onNavigationSettingsChange(
                    navigationSettings.copy(blurAmount = it),
                )
            },
        )

        Divider()

        Text(
            text = "While navigating between screens, enable or disable alpha animation of screens",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(Modifier.height(16.dp))

        SettingsSwitch(
            title = "Alpha animate",
            isSelected = navigationSettings.alphaEnabled,
            onSelectedChange = {
                onNavigationSettingsChange(
                    navigationSettings.copy(alphaEnabled = it),
                )
            },
        )

        Spacer(Modifier.height(16.dp))

        AnimationSlider(
            modifier = Modifier,
            range = 1f..10f,
            steps = 10,
            amount = navigationSettings.alphaAmount * 10,
            title = "Alpha Amount",
            onValueChange = {
                onNavigationSettingsChange(
                    navigationSettings.copy(alphaAmount = it / 10),
                )
            },
        )

        Divider()

        TextButton(
            modifier =
                Modifier
                    .wrapContentWidth()
                    .align(Alignment.End),
            onClick = { onNavigationSettingsChange(NavigationSettings()) },
        ) {
            Text(text = "Reset Navigation Defaults")
        }

        Divider()

        TextButton(
            modifier =
                Modifier
                    .wrapContentWidth()
                    .align(Alignment.End),
            onClick = onDone,
        ) {
            Text(text = "Ok")
        }
    }
}

@Composable
private fun Divider() {
    HorizontalDivider(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
    )
}

@Composable
private fun SettingsSwitch(
    title: String,
    isSelected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Disable animated effects",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(Modifier.weight(1f))
        Switch(
            isSelected = isSelected,
            onSelectedChange = onSelectedChange,
        )
    }
}
