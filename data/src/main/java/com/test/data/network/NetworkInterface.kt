package com.test.data.network

import com.test.data.network.entity.DevicesResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface NetworkInterface {

    @GET("test_android/items.test")
    fun getEntities() : Observable<DevicesResponse>
}