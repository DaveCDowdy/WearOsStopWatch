package com.daviddowdy.wearosstopwatchpoc.di

import com.daviddowdy.wearosstopwatchpoc.domain.usecase.GetTimerFlowUseCase
import com.daviddowdy.wearosstopwatchpoc.domain.usecase.ResetTimerUseCase
import com.daviddowdy.wearosstopwatchpoc.domain.usecase.ToggleTimerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGetTimerFlowUseCase(): GetTimerFlowUseCase {
        return GetTimerFlowUseCase()
    }

    @Provides
    fun provideResetTimerUseCase(): ResetTimerUseCase {
        return ResetTimerUseCase()
    }

    @Provides
    fun provideToggleTimerUseCase(): ToggleTimerUseCase {
        return ToggleTimerUseCase()
    }
}
