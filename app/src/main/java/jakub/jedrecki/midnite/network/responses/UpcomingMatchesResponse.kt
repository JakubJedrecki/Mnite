package jakub.jedrecki.midnite.network.responses

import com.google.gson.annotations.SerializedName
import jakub.jedrecki.midnite.network.model.MatchDto

class UpcomingMatchesResponse(
    @SerializedName("data")
    var matches: List<MatchDto>
)