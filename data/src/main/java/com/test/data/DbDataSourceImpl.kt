package com.test.data

import com.test.data.db.DbDao
import com.test.data.db.EntityDao
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

/**
 * Implementation of the interface
 */
class DbDataSourceImpl @Inject constructor(private val dbDao: DbDao) : DbDataSource {
    override fun getEntities(): Observable<List<EntityDao>> {
        return dbDao.gelAllEntities()
    }

    override fun updateEntity(entityDao: EntityDao) {
         dbDao.updateEntity(entityDao)
    }

    override fun deleteEntity(entityDao: EntityDao) {
         dbDao.deleteEntity(entityDao)
    }

    override fun insertEntity(entityDao: EntityDao) {
         dbDao.insertEntity(entityDao)
    }

    override fun deleteAll() {
        dbDao.deleteAll()
    }
}