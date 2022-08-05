package jakub.jedrecki.midnite.domain.model

data class Match(
    val id: Int,
    val name: String,
    val homeTeamName: String,
    val homeTeamId: Int,
    val awayTeamName: String,
    val awayTeamId: Int,
    val market: Market
)