package com.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [EntityDao::class], version = 2, exportSchema = false)
abstract class EntityDb : RoomDatabase() {
    abstract fun dbDao(): DbDao
}