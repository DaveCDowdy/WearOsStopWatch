package com.daviddowdy.wearosstopwatchpoc.domain

import com.daviddowdy.wearosstopwatchpoc.domain.usecase.GetTimerFlowUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class GetTimerFlowUseCaseTest {

    private val useCase = GetTimerFlowUseCase()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testEmitsTimeFlow() = runTest {
        val useCase = GetTimerFlowUseCase()

        val emissions = mutableListOf<Long>()

        val job = launch {
            useCase(true).take(3).collect {
                emissions.add(it)
            }
        }

        // Advance virtual time so the delays inside the flow can proceed
        repeat(3) {
            advanceTimeBy(10L)
            runCurrent()
        }

        job.cancel()

        assertTrue(emissions.isNotEmpty())
        assertTrue(emissions.size == 3)
        assertTrue(emissions.all { it >= 0 })
    }


    @Test
    fun testNoEmissionWhenStopped() = runTest {
        val flow = useCase.invoke(isRunning = false)
        val emitted = flow.take(1).toList()
        assertTrue(emitted.isEmpty())
    }
}
