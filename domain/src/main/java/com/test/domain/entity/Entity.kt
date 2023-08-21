package com.test.domain.entity

data class Entity(
    var title : String? = null,
    val pk_device : Long?,
    val macAddress : String?,
    val pk_deviceType : Int?,
    val pk_deviceSubType : Int?,
    val server_device : String?,
    val server_event : String?,
    val pk_account : Long?,
    val firmware : String?,
    val server_account : String?,
    val internalIp : String?,
    val lastAliveReported : String?,
    val platform : String?
)