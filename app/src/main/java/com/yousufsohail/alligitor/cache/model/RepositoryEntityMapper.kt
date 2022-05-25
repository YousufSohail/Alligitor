package com.yousufsohail.alligitor.cache.model

import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.domain.util.DomainMapper

class RepositoryEntityMapper : DomainMapper<RepositoryEntity, Repository> {

    override fun mapToDomainModel(model: RepositoryEntity): Repository {
        return Repository(
            model.id,
            model.name,
            model.description,
            model.language,
            model.stargazersCount,
            model.ownerName,
            model.ownerAvatarUrl
        )
    }

    override fun mapFromDomainModel(domainModel: Repository): RepositoryEntity {
        return RepositoryEntity(
            domainModel.id,
            domainModel.name,
            domainModel.description,
            domainModel.language,
            domainModel.stargazersCount,
            domainModel.ownerName,
            domainModel.ownerAvatarUrl
        )
    }
}
