package com.test.data.network.entity

import com.test.data.db.EntityDao
import com.test.domain.entity.Entity

fun EntityDao.toEntity() : Entity = Entity(title = title, pk_device, macAddress, pk_deviceType, pk_deviceSubType,
    server_device, server_event, pk_account, firmware, server_account, internalIp,
    lastAliveReported, platform)

fun Entity.toEntityDao() : EntityDao = EntityDao(title = title, pk_device = pk_device, macAddress = macAddress, pk_deviceType = pk_deviceType,
    pk_deviceSubType = pk_deviceSubType,
    server_device = server_device, server_event = server_event, pk_account = pk_account,
    firmware = firmware, server_account = firmware, internalIp = internalIp,
    lastAliveReported = lastAliveReported, platform = platform)

fun EntityResponse.toEntity() : Entity = Entity(title = null, pk_device, macAddress, pk_deviceType, pk_deviceSubType, server_device, server_event, pk_account, firmware, server_account, internalIp, lastAliveReported, platform)