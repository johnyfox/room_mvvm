package com.test.myapplication.list

import com.test.domain.entity.Entity

interface EntityClickListener {
    fun onItemClicked(entity: Entity)
    fun onEditClicked(entity: Entity)
    fun onLongClick(entity: Entity)
}