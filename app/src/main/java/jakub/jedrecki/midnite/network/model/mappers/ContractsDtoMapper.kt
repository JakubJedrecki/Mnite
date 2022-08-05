package jakub.jedrecki.midnite.network.model.mappers

import jakub.jedrecki.midnite.domain.model.Contract
import jakub.jedrecki.midnite.domain.util.DomainMapper
import jakub.jedrecki.midnite.network.model.ContractDto
import javax.inject.Inject

class ContractsDtoMapper @Inject constructor(): DomainMapper<ContractDto, Contract> {

    override fun mapToDomainModel(model: ContractDto): Contract {
        return Contract(
                contractId = model.contractId,
                marketId = model.marketId,
                name = model.name,
                maxBet = model.maxBet,
                price = model.price,
                status = model.status
            )
    }

    override fun mapFromDomainModel(domainModel: Contract): ContractDto {
        TODO("Not yet implemented")
    }

    fun toDomainList(initial: List<ContractDto>): List<Contract> {
        return initial.map { mapToDomainModel(it) }
    }
}