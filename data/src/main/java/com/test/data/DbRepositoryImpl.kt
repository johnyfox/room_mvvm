package com.test.data

import com.test.data.network.entity.toEntity
import com.test.data.network.entity.toEntityDao
import com.test.domain.DBRepository
import com.test.domain.entity.Entity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor(private val dbDataSource: DbDataSource) : DBRepository {

    override fun getEntities(): Observable<List<Entity>> {
        return dbDataSource.getEntities().map {
            it.map { it.toEntity() }
        }
    }

    override fun updateEntity(entity: Entity) {
         dbDataSource.updateEntity(entity.toEntityDao())
    }

    override fun deleteEntity(entity: Entity) {
         dbDataSource.deleteEntity(entity.toEntityDao())
    }

    override fun insertEntity(entity: Entity) {
         dbDataSource.insertEntity(entity.toEntityDao())
    }

    override fun deleteAll() {
        dbDataSource.deleteAll()
    }
}