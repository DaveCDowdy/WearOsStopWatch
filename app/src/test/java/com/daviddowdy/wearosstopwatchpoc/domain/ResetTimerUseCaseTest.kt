package com.daviddowdy.wearosstopwatchpoc.domain

import com.daviddowdy.wearosstopwatchpoc.domain.core.TimerStatus
import com.daviddowdy.wearosstopwatchpoc.domain.usecase.ResetTimerUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class ResetTimerUseCaseTest {

    private val useCase = ResetTimerUseCase()

    @Test
    fun `invoke returns TimerResetResult with RESET status and 0 elapsed time`() {
        val result = useCase()

        assertEquals(TimerStatus.RESET, result.status)
        assertEquals(0L, result.elapsedTime)
    }
}
