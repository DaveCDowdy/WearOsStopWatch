package com.daviddowdy.wearosstopwatchpoc.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.TimeTextDefaults
import com.daviddowdy.wearosstopwatchpoc.ui.view.StopWatch
import com.daviddowdy.wearosstopwatchpoc.ui.viewmodel.StopWatchViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.unit.sp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)
        setContent {
            val viewModel = hiltViewModel<StopWatchViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            Scaffold(
                timeText = {
                    TimeText(
                        timeTextStyle = TimeTextDefaults.timeTextStyle(
                            fontSize = 10.sp
                        )
                    )
                }
            ) {
                StopWatch(
                    state = uiState.timerStatus,
                    text = uiState.formattedTimer,
                    onToggleRunning = viewModel::toggleTimer,
                    onReset = viewModel::resetTimer,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    }
}
