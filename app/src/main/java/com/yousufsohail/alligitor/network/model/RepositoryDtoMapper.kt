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
            model.owner.login,
            model.owner.avatar_url
        )
    }

    override fun mapFromDomainModel(domainModel: Repository): RepositoryDto {
        return RepositoryDto(
            domainModel.id,
            domainModel.name,
            Owner(domainModel.userName, domainModel.userAvatar),
            domainModel.description,
            domainModel.language,
            domainModel.stargazersCount
        )
    }

    fun fromEntityList(initial: List<RepositoryDto>): List<Repository> {
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Repository>): List<RepositoryDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}
