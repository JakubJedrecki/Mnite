package jakub.jedrecki.midnite.domain.model

data class Contract(
    val contractId: Int,
    val marketId: Int,
    val name: String,
    val maxBet: String,
    val price: String,
    val status: String
)
