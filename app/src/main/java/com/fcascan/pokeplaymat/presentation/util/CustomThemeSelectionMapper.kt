package com.fcascan.pokeplaymat.presentation.util

import com.fcascan.pokeplaymat.R
import com.fcascan.pokeplaymat.model.CustomThemeSelection

object CustomThemeSelectionMapper {
    fun mapToCustomThemeSelection(theme: String?): CustomThemeSelection? {
        return when (theme?.uppercase()) {
//            "DESERT" -> CustomThemeSelection.DESERT
            "FOREST" -> CustomThemeSelection.FOREST
//            "HOSPITAL" -> CustomThemeSelection.HOSPITAL
//            "LEAGUE" -> CustomThemeSelection.LEAGUE
            "RIVER" -> CustomThemeSelection.RIVER
//            "SAFARI" -> CustomThemeSelection.SAFARI
            "SEA" -> CustomThemeSelection.SEA
//            "SNOW" -> CustomThemeSelection.SNOW
            "STADIUM" -> CustomThemeSelection.STADIUM
            "TOWN" -> CustomThemeSelection.TOWN
//            "WORLD_CHAMPIONSHIP", "WORLD CHAMPIONSHIP" -> CustomThemeSelection.WORLD_CHAMPIONSHIP
            else -> null
        }
    }

    fun mapToStringResId(theme: CustomThemeSelection?): Int {
        return when (theme) {
//            CustomThemeSelection.DESERT -> R.string.option_desert
            CustomThemeSelection.FOREST -> R.string.option_forest
//            CustomThemeSelection.HOSPITAL -> R.string.option_hospital
            CustomThemeSelection.RIVER -> R.string.option_river
//            CustomThemeSelection.SAFARI -> R.string.option_safari
            CustomThemeSelection.SEA -> R.string.option_sea
//            CustomThemeSelection.SNOW -> R.string.option_snow
            CustomThemeSelection.STADIUM -> R.string.option_stadium
            CustomThemeSelection.TOWN -> R.string.option_town
//            CustomThemeSelection.WORLD_CHAMPIONSHIP -> R.string.option_world_championship
            else -> R.string.select_option
        }
    }

    fun mapToDrawableResId(theme: CustomThemeSelection?): Int {
        return when (theme) {
//            CustomThemeSelection.DESERT -> R.drawable.artwork_desert
            CustomThemeSelection.FOREST -> R.drawable.artwork_forest
//            CustomThemeSelection.HOSPITAL -> R.drawable.artwork_hospital
            CustomThemeSelection.RIVER -> R.drawable.artwork_river
//            CustomThemeSelection.SAFARI -> R.drawable.artwork_safari
            CustomThemeSelection.SEA -> R.drawable.artwork_sea
//            CustomThemeSelection.SNOW -> R.drawable.artwork_snow
            CustomThemeSelection.STADIUM -> R.drawable.artwork_stadium
            CustomThemeSelection.TOWN -> R.drawable.artwork_town
//            CustomThemeSelection.WORLD_CHAMPIONSHIP -> R.drawable.artwork_world_championship
            else -> R.drawable.artwork_river
        }
    }
}
