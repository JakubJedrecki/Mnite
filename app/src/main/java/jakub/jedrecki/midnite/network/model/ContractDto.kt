package jakub.jedrecki.midnite.network.model

import com.google.gson.annotations.SerializedName

data class ContractDto(
    @SerializedName("id")
    var contractId: Int,

    @SerializedName("market_id")
    var marketId: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("max_bet")
    var maxBet: String,

    @SerializedName("price")
    var price: String,

    @SerializedName("status")
    var status: String
)