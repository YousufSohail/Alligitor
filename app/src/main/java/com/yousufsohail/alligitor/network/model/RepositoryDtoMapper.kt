package com.yousufsohail.alligitor.network.model

import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.domain.util.DomainMapper

class RepositoryDtoMapper : DomainMapper<RepositoryDto, Repository> {

    override fun mapToDomainModel(model: RepositoryDto): Repository {
        return Repository(
            model.id,
            model.name,
            model.description,
            model.language.orEmpty(),
            model.stargazersCount,
            model.ownerDto.login,
            model.ownerDto.avatar_url
        )
    }

    override fun mapFromDomainModel(domainModel: Repository): RepositoryDto {
        return RepositoryDto(
            domainModel.id,
            domainModel.name,
            OwnerDto(domainModel.ownerName, domainModel.ownerAvatarUrl),
            domainModel.description,
            domainModel.language,
            domainModel.stargazersCount
        )
    }

    fun toDomainList(dtoList: List<RepositoryDto>): List<Repository> {
        return dtoList.map { mapToDomainModel(it) }
    }

    fun fromDomainList(domainList: List<Repository>): List<RepositoryDto> {
        return domainList.map { mapFromDomainModel(it) }
    }
}
