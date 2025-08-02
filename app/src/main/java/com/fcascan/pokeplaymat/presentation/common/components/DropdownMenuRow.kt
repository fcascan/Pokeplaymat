package com.fcascan.pokeplaymat.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DropdownMenuRow(
    modifier: Modifier = Modifier,
    primaryLabel: String,
    secondaryLabel: String? = null,
    optionList: List<String>,
    onSelectedThemeChange: (String) -> Unit = { },
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
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            secondaryLabel?.let{
                Text(
                    text = secondaryLabel,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary,
                )
            }
        }
        DropdownMenu(
            expanded = false,
            onDismissRequest = { }
        ) {
            optionList.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = { onSelectedThemeChange(option) },
                )
            }
        }
    }
}

@Preview
@Composable
private fun DropdownMenuRowPreview() {
    DropdownMenuRow(
        primaryLabel = "Select Option",
        secondaryLabel = "Choose your preferred option",
        optionList = listOf("Default", "Custom"),
        onSelectedThemeChange = {}
    )
}

@Preview
@Composable
private fun DropdownMenuRowWithoutSecondaryTextPreview() {
    DropdownMenuRow(
        primaryLabel = "Select Option",
        optionList = listOf("Default", "Custom"),
        onSelectedThemeChange = {}
    )
}
