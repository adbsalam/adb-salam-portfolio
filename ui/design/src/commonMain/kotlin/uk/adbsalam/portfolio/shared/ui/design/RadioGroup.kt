package uk.adbsalam.portfolio.shared.ui.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> RadioGroup(
    items: List<T>,
    title: (T) -> String,
    preSelect: T,
    onSelected: (T) -> Unit,
) {
    var selected by remember { mutableStateOf(preSelect) }
    Column {
        items.forEach { item ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (item == selected),
                        onClick = {
                            selected = item
                            onSelected(item)
                        },
                    ).padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = (item == selected),
                    onClick = {
                        selected = item
                        onSelected(item)
                    },
                )
                Text(
                    text = title(item),
                    modifier = Modifier.padding(start = 16.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}
