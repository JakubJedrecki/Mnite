package jakub.jedrecki.midnite.network.model

import com.google.gson.annotations.SerializedName

data class MarketDto(
    @SerializedName("name")
    var name: String,

    @SerializedName("contracts")
    var contracts: List<ContractDto>,

    @SerializedName("id")
    var marketId: Int,

    @SerializedName("match_id")
    var matchId: Int,

    @SerializedName("popular")
    var popular: Boolean,

    @SerializedName("status")
    var status: String
)