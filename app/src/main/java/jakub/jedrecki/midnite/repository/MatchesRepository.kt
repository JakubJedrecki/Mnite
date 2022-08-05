package jakub.jedrecki.midnite.repository

import jakub.jedrecki.midnite.domain.model.MatchDetail
import jakub.jedrecki.midnite.domain.model.Matches
import jakub.jedrecki.midnite.network.errors.NetworkErrors
import jakub.jedrecki.midnite.util.Outcome

interface MatchesRepository {

    suspend fun getUpcomingMatches(): Outcome<Matches, NetworkErrors>
    suspend fun getMatchDetails(matchId: Int): Outcome<MatchDetail, NetworkErrors>
}