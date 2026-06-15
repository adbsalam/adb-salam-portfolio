package uk.adbsalam.portfolio.shared.ui.design

import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import platform.UIKit.UIAction
import platform.UIKit.UIControlEventValueChanged
import platform.UIKit.UISwitch

@Composable
actual fun Switch(
    isSelected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
) {
    val currentOnSelectedChange = remember { onSelectedChange }
    val currentBackgroundColor = MaterialTheme.colorScheme.surface.toUIColor()
    val tintColor = MaterialTheme.colorScheme.primary.toUIColor()

    UIKitView(
        factory = {
            UISwitch().apply {
                this.backgroundColor = currentBackgroundColor

                setOn(isSelected, animated = false)

                val action =
                    UIAction.actionWithHandler { _ ->
                        currentOnSelectedChange(this.on)
                    }
                addAction(action, UIControlEventValueChanged)
            }
        },
        modifier = Modifier.width(64.dp),
        update = { uiSwitch ->
            uiSwitch.backgroundColor = currentBackgroundColor

            if (uiSwitch.on != isSelected) {
                uiSwitch.setOn(isSelected, animated = true)
            }
        },
        properties = UIKitInteropProperties(isInteractive = true, isNativeAccessibilityEnabled = true),
    )
}
