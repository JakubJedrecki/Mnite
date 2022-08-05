package jakub.jedrecki.midnite.network

import jakub.jedrecki.midnite.network.model.MatchDetailDto
import jakub.jedrecki.midnite.network.responses.UpcomingMatchesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MidniteService {

    @GET("matches/upcoming")
    suspend fun getUpcomingMatches(): UpcomingMatchesResponse

    @GET("matches/{id}")
    suspend fun getMatchDetails(
        @Path("id") id: Int
    ): MatchDetailDto
}