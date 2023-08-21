package com.test.data

import com.test.data.network.entity.toEntity
import com.test.domain.NetworkRepository
import com.test.domain.entity.Entity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val networkDataSource: NetworkDataSource) : NetworkRepository {
    override fun getEntities(): Observable<List<Entity>> {
        return networkDataSource.getEntities().map {
            it.map { it.toEntity() }
        }
    }
}