package com.yousufsohail.alligitor.cache.model

import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.domain.util.DomainMapper
import com.yousufsohail.alligitor.util.DateUtils

class RepositoryEntityMapper : DomainMapper<RepositoryEntity, Repository> {

    override fun mapToDomainModel(model: RepositoryEntity): Repository {
        return Repository(
            id = model.id,
            name = model.name,
            description = model.description,
            language = model.language,
            stargazersCount = model.stargazersCount,
            ownerName = model.ownerName,
            ownerAvatarUrl = model.ownerAvatarUrl,
            dateCreated = DateUtils.longToDate(model.dateCreated),
            dateUpdated = DateUtils.longToDate(model.dateUpdated)
        )
    }

    override fun mapFromDomainModel(domainModel: Repository): RepositoryEntity {
        return RepositoryEntity(
            id = domainModel.id,
            name = domainModel.name,
            description = domainModel.description,
            language = domainModel.language,
            stargazersCount = domainModel.stargazersCount,
            ownerName = domainModel.ownerName,
            ownerAvatarUrl = domainModel.ownerAvatarUrl,
            dateCreated = DateUtils.dateToLong(domainModel.dateCreated),
            dateUpdated = DateUtils.dateToLong(domainModel.dateUpdated),
            dateCached = DateUtils.dateToLong(DateUtils.createTimestamp())
        )
    }

    fun toDomainList(entityList: List<RepositoryEntity>): List<Repository> {
        return entityList.map { mapToDomainModel(it) }
    }

    fun fromDomainList(domainList: List<Repository>): List<RepositoryEntity> {
        return domainList.map { mapFromDomainModel(it) }
    }
}
