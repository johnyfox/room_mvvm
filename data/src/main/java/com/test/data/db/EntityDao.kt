package com.test.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entity in Room DB. If we need to change column name - do it as below for columnInfo.
 */
@Entity(tableName = "table1")
data class EntityDao (
    val title : String ?= null,
    @PrimaryKey(autoGenerate = false)
    val pk_device : Long?,
    @ColumnInfo("MacAddress")
    val macAddress : String?,
    @ColumnInfo("PK_DeviceType")
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