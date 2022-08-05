package jakub.jedrecki.midnite.network.model

import com.google.gson.annotations.SerializedName

data class MatchDto(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("home_team")
    var homeTeamName: String,

    @SerializedName("home_team_id")
    var homeTeamId: Int,

    @SerializedName("away_team")
    var awayTeamName: String,

    @SerializedName("away_team_id")
    var awayTeamId: Int,

    @SerializedName("market")
    var market: MarketDto
)