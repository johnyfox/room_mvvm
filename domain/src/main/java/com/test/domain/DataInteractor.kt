package com.test.domain

import com.test.domain.entity.Entity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Interactor or UseCase to work with data.
 * If we need to do all manipulation aka write to db after all data will be fetched - do it here.
 * Customize rxjava via Interface and make things more complicated to simple testing.
 */
class DataInteractor @Inject constructor(private val dbRepository: DBRepository, private val networkRepository: NetworkRepository) {

    fun getEntities(): Observable<List<Entity>> {
        return networkRepository.getEntities().subscribeOn(Schedulers.io())
            .doOnEach {
                it.error
            }
            .map { list ->
                list.forEachIndexed { index, entity -> entity.title = "Home Number $index" }
            if (list.isNotEmpty()) {
                list.forEach { dbRepository.insertEntity(it) }
            }
            list
        }
    }

    fun getDBEntities(): Observable<List<Entity>> {
        return dbRepository.getEntities().subscribeOn(Schedulers.io())
    }

    fun updateEntity(entity: Entity) : Observable<Unit> {
        return Observable.fromCallable {
            dbRepository.updateEntity(entity)
        }.subscribeOn(Schedulers.io())
    }

    fun deleteEntity(entity: Entity) : Observable<Unit> {
        return Observable.fromCallable {
            dbRepository.deleteEntity(entity)
        }.subscribeOn(Schedulers.io())
    }

    fun insertEntity(entity: Entity) : Observable<Unit> {
        return Observable.fromCallable {
            dbRepository.insertEntity(entity)
        }.subscribeOn(Schedulers.io())
    }

    fun deleteAll() : Observable<Unit> {
        return Observable.fromCallable {
            dbRepository.deleteAll()
        }.subscribeOn(Schedulers.io())
    }

}