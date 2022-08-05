package jakub.jedrecki.midnite.domain.util

import jakub.jedrecki.midnite.domain.model.Matches
import jakub.jedrecki.midnite.network.model.MatchDto

interface DomainMapper <T, DomainModel> {
    fun mapToDomainModel(model: T): DomainModel
    fun mapFromDomainModel(domainModel: DomainModel): T
}