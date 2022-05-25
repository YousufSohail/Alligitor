package com.yousufsohail.alligitor.network.model

import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.domain.util.EntityMapper

class RepositoryDtoMapper : EntityMapper<RepositoryDto, Repository> {

    override fun mapFromEntity(dto: RepositoryDto): Repository {
        return Repository(
            dto.id,
            dto.name,
            dto.description,
            dto.language,
            dto.stargazersCount,
            dto.owner?.login,
            dto.owner?.avatar_url
        )
    }

    override fun mapToEntity(domainModel: Repository): RepositoryDto {
        return RepositoryDto(
            domainModel.id,
            domainModel.name,
            Owner(null, domainModel.userName, domainModel.userAvatar),
            domainModel.description,
            domainModel.language,
            domainModel.stargazersCount
        )
    }

    fun fromEntityList(initial: List<RepositoryDto>): List<Repository> {
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<Repository>): List<RepositoryDto> {
        return initial.map { mapToEntity(it) }
    }
}
