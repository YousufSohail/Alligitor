package com.yousufsohail.alligitor.network.model

import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.domain.util.DomainMapper
import com.yousufsohail.alligitor.util.DateUtils

class RepositoryDtoMapper : DomainMapper<RepositoryDto, Repository> {

    override fun mapToDomainModel(model: RepositoryDto): Repository {
        return Repository(
            id = model.id,
            name = model.name,
            description = model.description,
            language = model.language.orEmpty(),
            stargazersCount = model.stargazersCount,
            ownerName = model.ownerDto.login,
            ownerAvatarUrl = model.ownerDto.avatar_url,
            dateCreated = DateUtils.stringToDate(model.createdAt),
            dateUpdated = DateUtils.stringToDate(model.updatedAt)
        )
    }

    override fun mapFromDomainModel(domainModel: Repository): RepositoryDto {
        return RepositoryDto(
            id = domainModel.id,
            name = domainModel.name,
            ownerDto = OwnerDto(domainModel.ownerName, domainModel.ownerAvatarUrl),
            description = domainModel.description,
            language = domainModel.language,
            stargazersCount = domainModel.stargazersCount,
            createdAt = DateUtils.dateToString(domainModel.dateCreated),
            updatedAt = DateUtils.dateToString(domainModel.dateUpdated)
        )
    }

    fun toDomainList(dtoList: List<RepositoryDto>): List<Repository> {
        return dtoList.map { mapToDomainModel(it) }
    }

    fun fromDomainList(domainList: List<Repository>): List<RepositoryDto> {
        return domainList.map { mapFromDomainModel(it) }
    }
}
