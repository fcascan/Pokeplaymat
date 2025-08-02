package com.fcascan.pokeplaymat

import androidx.lifecycle.ViewModel
import com.fcascan.pokeplaymat.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferencesUtil,
) : ViewModel() {
    companion object {
        private val TAG = MainActivityViewModel::class.java.simpleName
    }

    var isDarkTheme = MutableStateFlow(sharedPreferences.getIsDarkTheme())
    fun setIsDarkTheme(newVal: Boolean) { isDarkTheme.value = newVal }

    var isHorizontal = MutableStateFlow(sharedPreferences.getIsHorizontal())
        private set

    var selectedTheme = MutableStateFlow(sharedPreferences.getSelectedTheme())
        private set
}
