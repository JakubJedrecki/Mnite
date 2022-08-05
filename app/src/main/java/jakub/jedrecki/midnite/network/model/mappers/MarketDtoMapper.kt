package jakub.jedrecki.midnite.network.model.mappers

import jakub.jedrecki.midnite.domain.model.Contract
import jakub.jedrecki.midnite.domain.model.Market
import jakub.jedrecki.midnite.domain.util.DomainMapper
import jakub.jedrecki.midnite.network.model.ContractDto
import jakub.jedrecki.midnite.network.model.MarketDto
import javax.inject.Inject

class MarketDtoMapper @Inject constructor(private val mapper: ContractsDtoMapper): DomainMapper<MarketDto, Market> {

    override fun mapToDomainModel(model: MarketDto): Market {
        return Market(
            name = model.name,
            contracts = mapper.toDomainList(model.contracts),
            marketId = model.marketId,
            matchId = model.matchId,
            popular = model.popular,
            status = model.status
        )
    }

    override fun mapFromDomainModel(domainModel: Market): MarketDto {
        TODO("Not yet implemented")
    }

    fun toDomainList(initial: List<MarketDto>): List<Market> {
        return initial.map { mapToDomainModel(it) }
    }
}