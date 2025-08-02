package com.fcascan.pokeplaymat.presentation.common.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.fcascan.pokeplaymat.R
import com.fcascan.pokeplaymat.presentation.ui.dimen.BorderWidth
import com.fcascan.pokeplaymat.presentation.ui.dimen.Padding
import com.fcascan.pokeplaymat.presentation.ui.dimen.Size
import com.fcascan.pokeplaymat.presentation.ui.dimen.Spacing

@Composable
internal fun OptionRowChip(
    modifier: Modifier = Modifier,
    @StringRes labelResId: Int = R.string.select_option,
    @StringRes secondaryLabelResId: Int? = null,
    selectedOption: String?,
    options: List<String>,
    onOptionSelected: (String?) -> Unit,
) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var chipSelected by rememberSaveable { mutableStateOf(selectedOption != null) }

    val chipColors = SelectableChipColors(
        containerColor = colorScheme.inverseOnSurface,
        labelColor = colorScheme.onSurfaceVariant,
        leadingIconColor = colorScheme.onSurfaceVariant,
        trailingIconColor = colorScheme.onSurfaceVariant,
        disabledContainerColor = colorScheme.surfaceContainerLowest,
        disabledLabelColor = colorScheme.surface,
        disabledLeadingIconColor = colorScheme.surface,
        disabledTrailingIconColor = colorScheme.surface,
        disabledSelectedContainerColor = colorScheme.surfaceContainerLowest,
        selectedLabelColor = colorScheme.inverseOnSurface,
        selectedLeadingIconColor = colorScheme.inverseOnSurface,
        selectedContainerColor = colorScheme.onSurface,
        selectedTrailingIconColor = colorScheme.inverseOnSurface,
    )

    val bottomSpacer = when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> Spacing.Large
        Configuration.ORIENTATION_LANDSCAPE -> Spacing.Small
        else -> Spacing.Medium
    }

    if (showBottomSheet) {
        OptionSelectionBottomSheet(
            titleResId = labelResId,
            bottomSpacer = bottomSpacer,
            options = options,
            onOptionSelected = {
                chipSelected = it != null
                onOptionSelected(it)
                showBottomSheet = false
            },
            onDismiss = {
                showBottomSheet = false
            }
        )
    }

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
                text = stringResource(id = labelResId),
                style = typography.titleMedium,
                color = colorScheme.onPrimary,
            )
            secondaryLabelResId?.let{
                Text(
                    text = stringResource(id = it),
                    style = typography.bodySmall,
                    color = colorScheme.onSecondary,
                )
            }
        }
        InputChip(
            modifier = modifier
                .height(Size.Small),
            selected = chipSelected,
            onClick = { showBottomSheet = true },
            border = BorderStroke(
                width = BorderWidth.Neutral,
                color = colorScheme.onSurfaceVariant,
            ),
            label = {
                Text(
                    text = selectedOption ?: stringResource(labelResId),
                    style = typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            leadingIcon = {
                if (chipSelected) {
                    SegmentedButtonDefaults.ActiveIcon()
                }
            },
            trailingIcon = {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "Arrow Drop Down",
                    Modifier.size(Size.Tiny)
                )
            },
            colors = chipColors
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OptionSelectionBottomSheet(
    titleResId: Int,
    bottomSpacer: Dp,
    options: List<String>,
    onOptionSelected: (String?) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        dragHandle = null,
        content = {
            Column(modifier = Modifier
                .padding(Padding.Medium)
            ) {
                Row(
                    modifier = Modifier
                        .height(Size.Large)
                        .padding(
                            vertical = Padding.Tiny,
                            horizontal = Padding.ExtraSmall,
                        )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Deselect option",
                        modifier = Modifier
                            .padding(Padding.Tiny)
                            .clickable { onOptionSelected(null) }
                            .size(Size.ExtraSmall)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(
                            top = Padding.Tiny,
                            bottom = Padding.Tiny,
                            start = Padding.Medium,
                        )
                ) {
                    Text(
                        text = stringResource(titleResId),
                        style = typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(Size.Small))
                    options.forEach { option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = Padding.Medium)
                                .background(Color.Transparent)
                                .clickable { onOptionSelected(option) },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = option,
                                style = typography.labelMedium
                            )
                            Icon(
                                imageVector = Icons.Outlined.ArrowDropDown,
                                contentDescription = "Select $option filter option",
                                modifier = Modifier
                                    .padding(Padding.Tiny)
                                    .size(Size.ExtraSmall)
                            )
                        }
                    }
                    Spacer(modifier = Modifier
                        .height(bottomSpacer)
                        .fillMaxWidth(),
                    )
                }
            }
        }
    )
    Spacer(modifier = Modifier.height(bottomSpacer))
}

@Preview
@Composable
private fun OptionRowChipPreview() {
    OptionRowChip(
        selectedOption = null,
        options = listOf("Option 1", "Option 2", "Option 3"),
        onOptionSelected = {},
    )
}

@Preview
@Composable
private fun OptionRowChipSelectedPreview() {
    OptionRowChip(
        selectedOption = "Option 1",
        options = listOf("Option 1", "Option 2", "Option 3"),
        onOptionSelected = {},
    )
}

@Preview
@Composable
private fun OptionSelectionBottomSheetPreview() {
    Column(modifier = Modifier.padding(Padding.Medium)) {
        OptionSelectionBottomSheet(
            titleResId = R.string.select_option,
            bottomSpacer = Size.Large,
            options = listOf("Option 1", "Option 2", "Option 3"),
            onOptionSelected = {},
            onDismiss = {}
        )
    }
}
