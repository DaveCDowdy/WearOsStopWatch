import com.daviddowdy.wearosstopwatchpoc.domain.core.TimerStatus
import com.daviddowdy.wearosstopwatchpoc.domain.usecase.ToggleTimerUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class ToggleTimerUseCaseTest {

    private val toggleTimerUseCase = ToggleTimerUseCase()

    @Test
    fun `when currentStatus is RUNNING then returns PAUSED`() {
        val result = toggleTimerUseCase(TimerStatus.RUNNING)
        assertEquals(TimerStatus.PAUSED, result)
    }

    @Test
    fun `when currentStatus is PAUSED then returns RUNNING`() {
        val result = toggleTimerUseCase(TimerStatus.PAUSED)
        assertEquals(TimerStatus.RUNNING, result)
    }

    @Test
    fun `when currentStatus is RESET then returns RUNNING`() {
        val result = toggleTimerUseCase(TimerStatus.RESET)
        assertEquals(TimerStatus.RUNNING, result)
    }
}
