package com.test.data

import com.test.data.network.entity.EntityResponse
import io.reactivex.rxjava3.core.Observable

interface NetworkDataSource {
    fun getEntities() : Observable<List<EntityResponse>>
}