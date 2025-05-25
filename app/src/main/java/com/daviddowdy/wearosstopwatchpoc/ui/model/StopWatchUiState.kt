package com.daviddowdy.wearosstopwatchpoc.ui.model

import com.daviddowdy.wearosstopwatchpoc.domain.core.TimerStatus

data class StopWatchUiState(
    val timerStatus: TimerStatus = TimerStatus.RESET,
    val elapsedTimeMillis: Long = 0L,
    val formattedTimer: String = "00:00:00:000"
)