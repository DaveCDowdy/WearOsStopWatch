package com.daviddowdy.wearosstopwatchpoc.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTimerFlowUseCase @Inject constructor() {
    operator fun invoke(isRunning: Boolean): Flow<Long> = flow {
        var startMillis = System.currentTimeMillis()
        while (isRunning) {
            val currentMillis = System.currentTimeMillis()
            val timeDiff = if (currentMillis > startMillis) currentMillis - startMillis else 0L
            emit(timeDiff)
            startMillis = currentMillis
            delay(10L)
        }
    }
}