package com.fcascan.pokeplaymat.utils.extension

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.setTagAndId(tag: String?): Modifier {
    return tag?.let {
        this
            .semantics { this.testTagsAsResourceId = true }
            .testTag("android:id/$it")
    } ?: this
}
