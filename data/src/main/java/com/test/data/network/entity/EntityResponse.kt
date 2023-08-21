package com.test.data.network.entity

import com.google.gson.annotations.SerializedName


data class EntityResponse(
    @SerializedName("PK_Device")
    val pk_device : Long?,
    @SerializedName("MacAddress")
    val macAddress : String?,
    @SerializedName("PK_DeviceType")
    val pk_deviceType : Int?,
    @SerializedName("PK_DeviceSubType")
    val pk_deviceSubType : Int?,
    @SerializedName("Server_Device")
    val server_device : String?,
    @SerializedName("Server_Event")
    val server_event : String?,
    @SerializedName("PK_Account")
    val pk_account : Long?,
    @SerializedName("Firmware")
    val firmware : String?,
    @SerializedName("Server_Account")
    val server_account : String?,
    @SerializedName("InternalIP")
    val internalIp : String?,
    @SerializedName("LastAliveReported")
    val lastAliveReported : String?,
    @SerializedName("Platform")
    val platform : String?
)

data class DevicesResponse(
    @SerializedName("Devices")
    val devices : List<EntityResponse>
)