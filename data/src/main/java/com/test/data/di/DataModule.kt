package com.test.data.di

import com.test.data.*
import com.test.data.db.DbDao
import com.test.data.network.NetworkInterface
import com.test.domain.DBRepository
import com.test.domain.DataInteractor
import com.test.domain.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun networkDataSource(networkInterface: NetworkInterface) : NetworkDataSource {
        return NetworkDataSourceImpl(networkInterface)
    }

    @Provides
    @Singleton
    fun dbDataSource(dbDao: DbDao) : DbDataSource {
        return DbDataSourceImpl(dbDao)
    }

    @Provides
    @Singleton
    fun dbDataRepository(dbDataSource: DbDataSource) : DBRepository {
        return DbRepositoryImpl(dbDataSource)
    }

    @Provides
    @Singleton
    fun dbNetworkRepository(networkDataSource: NetworkDataSource) : NetworkRepository {
        return NetworkRepositoryImpl(networkDataSource)
    }

    @Provides
    @Singleton
    fun dataInteractor(dbRepository: DBRepository, networkRepository: NetworkRepository) : DataInteractor {
        return DataInteractor(dbRepository, networkRepository)
    }
}