package com.fcascan.pokeplaymat.model

enum class ThemeSelection {
    LIGHT,
    DARK,
    SYSTEM;

    companion object {
        fun fromString(value: String): ThemeSelection {
            return when (value.uppercase()) {
                "LIGHT" -> LIGHT
                "DARK" -> DARK
                "SYSTEM" -> SYSTEM
                else -> SYSTEM
            }
        }
    }
}
