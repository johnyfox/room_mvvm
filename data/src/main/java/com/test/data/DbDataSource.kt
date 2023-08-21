package com.test.data

import com.test.data.db.EntityDao
import io.reactivex.rxjava3.core.Observable

/**
 * All methods to work with DB
 */
interface DbDataSource {

    fun getEntities() : Observable<List<EntityDao>>
    fun updateEntity(entityDao: EntityDao)
    fun deleteEntity(entityDao: EntityDao)
    fun insertEntity(entityDao: EntityDao)
    fun deleteAll()

}