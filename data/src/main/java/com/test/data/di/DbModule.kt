package com.test.data.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.test.data.db.DbDao
import com.test.data.db.EntityDao
import com.test.data.db.EntityDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideEntityDB(@ApplicationContext context: Context): EntityDb {
        return databaseBuilder(context, EntityDb::class.java, "Database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideEntityDao(entityDb: EntityDb): DbDao {
        return entityDb.dbDao()
    }
}