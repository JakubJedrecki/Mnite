package jakub.jedrecki.midnite.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import jakub.jedrecki.midnite.network.errors.NetworkErrors
import jakub.jedrecki.midnite.repository.MatchesRepository
import jakub.jedrecki.midnite.util.Outcome
import jakub.jedrecki.midnite.util.getOrAwaitValue
import jakub.jedrecki.midnite.util.getValueForTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val dispatcher = StandardTestDispatcher()

    @RelaxedMockK
    private lateinit var repository: MatchesRepository

    private lateinit var viewModel: HomeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)

        viewModel = HomeViewModel(repository)
    }

    @Test
    fun `getUpcomingMatches host error`() {
        coEvery { repository.getUpcomingMatches() } returns Outcome.error(NetworkErrors.UNKNOWN_HOST_ERROR())

        viewModel.getUpcomingMatches()
        val matches = viewModel.upcomingMatches.getValueForTest()
        val error = viewModel.errorMessage.getOrAwaitValue()

        Truth.assertThat(matches?.data).isNull()
        Truth.assertThat(error).isNotNull()
    }
}