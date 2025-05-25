@file:OptIn(ExperimentalCoroutinesApi::class)

package com.daviddowdy.wearosstopwatchpoc.ui.viewmodel

import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.Dispatchers
import org.junit.After

@OptIn(ExperimentalCoroutinesApi::class)
class StopWatchViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: StopWatchViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        viewModel = StopWatchViewModel(
            getTimerFlowUseCase = mockk(),
            resetTimerUseCase = mockk(),
            toggleTimerUseCase = mockk()
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // always reset after test
    }

    @Test
    fun `some test`() = runTest {
        // Run test using testDispatcher
    }
}


