package jakub.jedrecki.midnite.domain.model

data class Market(
    val name: String,
    val contracts: List<Contract>,
    val marketId: Int,
    val matchId: Int,
    val popular: Boolean,
    val status: String
)
