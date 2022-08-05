package jakub.jedrecki.midnite.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import jakub.jedrecki.midnite.network.MidniteService
import jakub.jedrecki.midnite.network.model.mappers.ContractsDtoMapper
import jakub.jedrecki.midnite.network.model.mappers.MarketDtoMapper
import jakub.jedrecki.midnite.network.model.mappers.MatchDetailDtoMapper
import jakub.jedrecki.midnite.network.model.mappers.MatchesDtoMapper
import jakub.jedrecki.midnite.network.responses.UpcomingMatchesResponse
import jakub.jedrecki.midnite.util.Outcome
import jakub.jedrecki.midnite.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test


internal class MatchesRepositoryImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val dispatcher = StandardTestDispatcher()

    @RelaxedMockK
    private lateinit var midniteService: MidniteService

    private lateinit var repository: MatchesRepository
    private val contractsDtoMapper = ContractsDtoMapper()
    private val marketDtoMapper = MarketDtoMapper(contractsDtoMapper)
    private val matchesDtoMapper = MatchesDtoMapper(marketDtoMapper)
    private val matchDetailDtoMapper = MatchDetailDtoMapper(marketDtoMapper)


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)

        repository = MatchesRepositoryImpl(midniteService, matchesDtoMapper, matchDetailDtoMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test aaa`() = runBlockingTest {
        coEvery { midniteService.getUpcomingMatches() } returns UpcomingMatchesResponse(emptyList())

        val response = repository.getUpcomingMatches()

        Truth.assertThat(response.status).isEqualTo(Status.SUCCESS)
    }
}