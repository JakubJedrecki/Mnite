package jakub.jedrecki.midnite.ui.matchDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import jakub.jedrecki.midnite.domain.model.MatchDetail
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

internal class MatchDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val dispatcher = StandardTestDispatcher()

    @RelaxedMockK
    private lateinit var repository: MatchesRepository

    private lateinit var viewModel: MatchDetailViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)

        viewModel = MatchDetailViewModel(repository)
    }

    @Test
    fun `getMatchDetails success`() {
        //markets shouldn't be empty but just for simplicity and time
        coEvery { repository.getMatchDetails(any()) } returns Outcome.success(
            MatchDetail(
                "home",
                "away",
                emptyList()
            )
        )

        viewModel.getMatchDetail(123)
        val matchDetails = viewModel.matchDetails.getOrAwaitValue()

        Truth.assertThat(matchDetails.homeTeam).isEqualTo("home")
        Truth.assertThat(matchDetails.awayTeam).isEqualTo("away")
        Truth.assertThat(matchDetails.markets).isEmpty()
    }

    @Test
    fun `getMatchDetails host error`() {
        coEvery { repository.getMatchDetails(any()) } returns Outcome.error(NetworkErrors.UNKNOWN_HOST_ERROR())

        viewModel.getMatchDetail(123)
        val matchDetails = viewModel.matchDetails.getValueForTest()
        val error = viewModel.errorMessage.getOrAwaitValue()

        Truth.assertThat(matchDetails).isNull()
        Truth.assertThat(error).isNotNull()
    }
}