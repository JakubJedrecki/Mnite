package jakub.jedrecki.midnite.domain.model

data class MatchDetail(
    val homeTeam: String,
    val awayTeam: String,
    val markets: List<Market>
)
