package com.daviddowdy.wearosstopwatchpoc.domain.usecase

import com.daviddowdy.wearosstopwatchpoc.domain.core.TimerStatus
import javax.inject.Inject

class ToggleTimerUseCase @Inject constructor() {
    operator fun invoke(currentStatus: TimerStatus): TimerStatus {
        return when (currentStatus) {
            TimerStatus.RUNNING -> TimerStatus.PAUSED
            TimerStatus.PAUSED, TimerStatus.RESET -> TimerStatus.RUNNING
        }
    }
}