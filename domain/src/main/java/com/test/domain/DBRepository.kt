package com.test.domain

import com.test.domain.entity.Entity
import io.reactivex.rxjava3.core.Observable

interface DBRepository {
    fun getEntities() : Observable<List<Entity>>
    fun updateEntity(entity: Entity)
    fun deleteEntity(entity: Entity)
    fun insertEntity(entity: Entity)
    fun deleteAll()
}