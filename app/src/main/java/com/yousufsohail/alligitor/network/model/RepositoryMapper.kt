package com.yousufsohail.alligitor.network.model

import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.domain.util.EntityMapper

class RepositoryMapper : EntityMapper<RepositoryNetworkEntity, Repository> {

    override fun mapFromEntity(entity: RepositoryNetworkEntity): Repository {
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

    override fun mapToEntity(domainModel: Repository): RepositoryNetworkEntity {
        return RepositoryNetworkEntity(
            domainModel.id,
            domainModel.name,
            Owner(null, domainModel.userName, domainModel.userAvatars),
            domainModel.description,
            domainModel.language,
            domainModel.stargazersCount
        )
    }

    fun fromEntityList(initial: List<RepositoryNetworkEntity>): List<Repository> {
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<Repository>): List<RepositoryNetworkEntity> {
        return initial.map { mapToEntity(it) }
    }
}
