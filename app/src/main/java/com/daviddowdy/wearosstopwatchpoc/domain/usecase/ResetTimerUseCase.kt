package com.daviddowdy.wearosstopwatchpoc.domain.usecase

import com.daviddowdy.wearosstopwatchpoc.domain.core.TimerResetResult
import com.daviddowdy.wearosstopwatchpoc.domain.core.TimerStatus
import javax.inject.Inject

class ResetTimerUseCase @Inject constructor() {
    operator fun invoke() = TimerResetResult(
        status = TimerStatus.RESET,
        elapsedTime = 0L
    )
}