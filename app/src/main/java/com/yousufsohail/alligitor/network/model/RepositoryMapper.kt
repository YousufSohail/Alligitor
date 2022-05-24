package com.yousufsohail.alligitor.network.model

import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.domain.util.EntityMapper

class RepositoryMapper : EntityMapper<RepositoryEntity, Repository> {

    override fun mapFromEntity(entity: RepositoryEntity): Repository {
        return Repository(
            entity.id,
            entity.name,
            entity.description,
            entity.language,
            entity.stargazersCount,
            entity.owner?.login,
            entity.owner?.avatar_url
        )
    }

    override fun mapToEntity(domainModel: Repository): RepositoryEntity {
        return RepositoryEntity(
            domainModel.id,
            domainModel.name,
            Owner(null, domainModel.userName, domainModel.userAvatar),
            domainModel.description,
            domainModel.language,
            domainModel.stargazersCount
        )
    }

    fun fromEntityList(initial: List<RepositoryEntity>): List<Repository> {
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<Repository>): List<RepositoryEntity> {
        return initial.map { mapToEntity(it) }
    }
}
