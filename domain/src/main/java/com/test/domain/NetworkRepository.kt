package com.test.domain

import com.test.domain.entity.Entity
import io.reactivex.rxjava3.core.Observable

interface NetworkRepository {
    fun getEntities() : Observable<List<Entity>>
}