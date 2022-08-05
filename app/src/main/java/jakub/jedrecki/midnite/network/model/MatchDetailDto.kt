package jakub.jedrecki.midnite.network.model

import com.google.gson.annotations.SerializedName

data class MatchDetailDto(
    @SerializedName("away_team")
    val homeTeam: String,

    @SerializedName("home_team")
    val awayTeam: String,

    @SerializedName("markets")
    val markets: List<MarketDto>
)