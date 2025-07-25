package com.fcascan.pokeplaymat.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

interface RestartableStateFlow<out T> : StateFlow<T> {
    fun restart()
}

fun <T> Flow<T>.restartableStateIn(
    scope: CoroutineScope,
    initialValue: T,
    started: SharingStarted = SharingStarted.WhileSubscribed(5000L),
): RestartableStateFlow<T> {
    val sharingRestartable = started.makeRestartable()
    val stateFlow = stateIn(scope, sharingRestartable, initialValue)
    return object : RestartableStateFlow<T>, StateFlow<T> by stateFlow {
        override fun restart() = sharingRestartable.restart()
    }
}
