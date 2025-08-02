package com.fcascan.pokeplaymat.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OptionRowSwitch(
    modifier: Modifier = Modifier,
    primaryLabel: String,
    secondaryLabel: String? = null,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = primaryLabel,
                style = typography.titleMedium,
                color = colorScheme.onPrimary,
            )
            secondaryLabel?.let{
                Text(
                    text = secondaryLabel,
                    style = typography.bodySmall,
                    color = colorScheme.onSecondary,
                )
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = {
                onCheckedChange(it)
            }
        )
    }
}

@Preview
@Composable
private fun OptionRowSwitchOnPreview() {
    OptionRowSwitch(
        primaryLabel = "Option1",
        secondaryLabel = "This is a secondary text",
        checked = true,
        onCheckedChange = {}
    )
}

@Preview
@Composable
private fun OptionRowSwitchOnWithoutSecondaryTextPreview() {
    OptionRowSwitch(
        primaryLabel = "Option1",
        checked = true,
        onCheckedChange = {}
    )
}

@Preview
@Composable
private fun OptionRowSwitchOffPreview() {
    OptionRowSwitch(
        primaryLabel = "Option2",
        secondaryLabel = "This is a secondary text",
        checked = false,
        onCheckedChange = {}
    )
}

@Preview
@Composable
private fun OptionRowSwitchOffWithoutSecondaryTextPreview() {
    OptionRowSwitch(
        primaryLabel = "Option2",
        checked = false,
        onCheckedChange = {}
    )
}
