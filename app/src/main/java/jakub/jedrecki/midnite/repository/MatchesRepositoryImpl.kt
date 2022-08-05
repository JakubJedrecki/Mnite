package jakub.jedrecki.midnite.repository

import jakub.jedrecki.midnite.domain.model.MatchDetail
import jakub.jedrecki.midnite.domain.model.Matches
import jakub.jedrecki.midnite.network.MidniteService
import jakub.jedrecki.midnite.network.errors.NetworkErrors
import jakub.jedrecki.midnite.network.model.mappers.MatchDetailDtoMapper
import jakub.jedrecki.midnite.network.model.mappers.MatchesDtoMapper
import jakub.jedrecki.midnite.util.Outcome
import java.net.UnknownHostException
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    private val midniteService: MidniteService,
    private val matchesMapper: MatchesDtoMapper,
    private val matchDetailMapper: MatchDetailDtoMapper
) : MatchesRepository {

    override suspend fun getUpcomingMatches(): Outcome<Matches, NetworkErrors> {
        return try {
            val result = midniteService.getUpcomingMatches().matches

            Outcome.success(Matches(data = matchesMapper.toDomainList(result)))
        } catch (exception: UnknownHostException) {
            exception.printStackTrace()
            Outcome.error(NetworkErrors.UNKNOWN_HOST_ERROR())
        } catch (exception: Exception) {
            exception.printStackTrace()
            Outcome.error(NetworkErrors.UNKNOWN_HOST_ERROR())
        }
    }

    override suspend fun getMatchDetails(matchId: Int): Outcome<MatchDetail, NetworkErrors> {
        return try {
            val result = midniteService.getMatchDetails(matchId)

            Outcome.success(matchDetailMapper.mapToDomainModel(result))
        } catch (exception: UnknownHostException) {
            exception.printStackTrace()
            Outcome.error(NetworkErrors.UNKNOWN_HOST_ERROR())
        } catch (exception: Exception) {
            exception.printStackTrace()
            Outcome.error(NetworkErrors.UNKNOWN_HOST_ERROR())
        }
    }
}