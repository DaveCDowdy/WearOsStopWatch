package com.daviddowdy.wearosstopwatchpoc.ui.viewmodel

import androidx.lifecycle.ViewModel
import java.time.format.DateTimeFormatter
import androidx.lifecycle.viewModelScope
import com.daviddowdy.wearosstopwatchpoc.domain.core.TimerStatus
import com.daviddowdy.wearosstopwatchpoc.domain.usecase.GetTimerFlowUseCase
import com.daviddowdy.wearosstopwatchpoc.domain.usecase.ResetTimerUseCase
import com.daviddowdy.wearosstopwatchpoc.domain.usecase.ToggleTimerUseCase
import com.daviddowdy.wearosstopwatchpoc.ui.model.StopWatchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class StopWatchViewModel @Inject constructor(
    private val getTimerFlowUseCase: GetTimerFlowUseCase,
    private val resetTimerUseCase: ResetTimerUseCase,
    private val toggleTimerUseCase: ToggleTimerUseCase,
) : ViewModel() {

    private val mutableUiState = MutableStateFlow(StopWatchUiState())
    val uiState = mutableUiState.asStateFlow()

    private var timerJob: Job? = null

    fun toggleTimer() {
        val newStatus = toggleTimerUseCase(mutableUiState.value.timerStatus)
        mutableUiState.update { it.copy(timerStatus = newStatus) }

        if (newStatus == TimerStatus.RUNNING) {
            startTimer()
        } else {
            stopTimer()
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            getTimerFlowUseCase(true).collect { deltaMillis ->
                mutableUiState.update { current ->
                    val updatedElapsed = current.elapsedTimeMillis + deltaMillis
                    current.copy(
                        elapsedTimeMillis = updatedElapsed,
                        formattedTimer = formatElapsedTime(updatedElapsed)
                    )
                }
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    fun resetTimer() {
        stopTimer()
        val resetResult = resetTimerUseCase()
        mutableUiState.update {
            it.copy(
                timerStatus = resetResult.status,
                elapsedTimeMillis = resetResult.elapsedTime,
                formattedTimer = "00:00:00:000"
            )
        }
    }

    private fun formatElapsedTime(elapsedMillis: Long): String {
        // Format elapsedMillis to string "H:mm:ss:SSS"
        val formatter = DateTimeFormatter.ofPattern("H:mm:ss:SSS")
        val time = LocalTime.ofNanoOfDay(elapsedMillis * 1_000_000)
        return time.format(formatter)
    }
}

