package com.fcascan.pokeplaymat.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcascan.pokeplaymat.model.CustomThemeSelection
import com.fcascan.pokeplaymat.utils.SharedPreferencesUtil
import com.fcascan.pokeplaymat.utils.restartableStateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferencesUtil,
) : ViewModel() {
    val mainScreenState = flow {
        emit(MainScreenState.Loading)
        runCatching {
            val successState = MainScreenState.Success(
                customTheme = sharedPreferences.getCustomTheme(),
                numberOfBenchedCards = sharedPreferences.getNumberOfBenchedCards(),
                playerName = sharedPreferences.getPlayerName(),
            )
            emit(successState)
        }.onFailure {
            emit(MainScreenState.Error(it))
        }
    }.restartableStateIn(
        scope = viewModelScope,
        initialValue = SettingsScreenState.Loading,
    )
}

sealed class MainScreenState {
    data object Loading : MainScreenState()
    data class Error(val error: Throwable) : MainScreenState()
    data class Success(
        val customTheme: CustomThemeSelection?,
        val numberOfBenchedCards: Int,
        val playerName: String?,
    ) : MainScreenState()
}
