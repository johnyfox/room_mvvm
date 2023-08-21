package com.test.data

import com.test.data.network.NetworkInterface
import com.test.data.network.entity.EntityResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val networkInterface: NetworkInterface) : NetworkDataSource {

    override fun getEntities(): Observable<List<EntityResponse>> {
        return networkInterface.getEntities().map {list ->
            list.devices
        }
    }
}