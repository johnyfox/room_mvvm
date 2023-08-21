package com.test.data.db

import androidx.room.*
import io.reactivex.rxjava3.core.Observable

@Dao
interface DbDao {
    @Insert
    fun insertEntity(entityDao: EntityDao)

    @Query("SELECT * FROM table1")
    fun gelAllEntities(): Observable<List<EntityDao>>

    @Update
    fun updateEntity(entityDao: EntityDao)

    @Delete
    fun deleteEntity(entityDao: EntityDao)

    @Query("DELETE FROM table1")
    fun deleteAll()
}