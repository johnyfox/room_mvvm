package com.test.myapplication.item

import com.test.domain.entity.Entity


data class State(
    val loading : Boolean = true,
    val item : Entity?= null,
    val wasItemSaved : Boolean = false
)