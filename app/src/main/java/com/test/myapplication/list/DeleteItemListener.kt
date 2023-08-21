package com.test.myapplication.list

import com.test.domain.entity.Entity

interface DeleteItemListener {
    fun deleteItem(entity: Entity)
}