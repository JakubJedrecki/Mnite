package jakub.jedrecki.midnite.network.model.mappers

import jakub.jedrecki.midnite.domain.model.MatchDetail
import jakub.jedrecki.midnite.domain.util.DomainMapper
import jakub.jedrecki.midnite.network.model.MatchDetailDto
import javax.inject.Inject

class MatchDetailDtoMapper @Inject constructor(private val mapper: MarketDtoMapper): DomainMapper<MatchDetailDto, MatchDetail>
{
    override fun mapToDomainModel(model: MatchDetailDto): MatchDetail {
        return MatchDetail(
            homeTeam = model.homeTeam,
            awayTeam = model.awayTeam,
            markets = mapper.toDomainList(model.markets)
        )
    }

    override fun mapFromDomainModel(domainModel: MatchDetail): MatchDetailDto {
        TODO("Not yet implemented")
    }
}