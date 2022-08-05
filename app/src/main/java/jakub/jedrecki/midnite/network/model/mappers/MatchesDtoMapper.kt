package jakub.jedrecki.midnite.network.model.mappers

import jakub.jedrecki.midnite.domain.model.Match
import jakub.jedrecki.midnite.domain.util.DomainMapper
import jakub.jedrecki.midnite.network.model.MatchDto
import javax.inject.Inject

class MatchesDtoMapper @Inject constructor(private val mapper: MarketDtoMapper) :
    DomainMapper<MatchDto, Match> {

    override fun mapToDomainModel(model: MatchDto): Match {
        return Match(
            id = model.id,
            name = model.name,
            homeTeamName = model.homeTeamName,
            homeTeamId = model.homeTeamId,
            awayTeamId = model.awayTeamId,
            awayTeamName = model.awayTeamName,
            market = mapper.mapToDomainModel(model.market)
        )
    }

    override fun mapFromDomainModel(domainModel: Match): MatchDto {
        TODO("Not yet implemented")
    }

    fun toDomainList(initial: List<MatchDto>): List<Match> {
        return initial.map { mapToDomainModel(it) }
    }
}