package com.test.myapplication.list

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.test.domain.DataInteractor
import com.test.domain.entity.Entity
import com.test.myapplication.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val dataInteractor: DataInteractor) : BaseViewModel() {

    private val _state : MutableLiveData<List<Entity>> = MutableLiveData(emptyList())
    val state : MutableLiveData<List<Entity>> = _state

    init {
        fetchData()
    }

    private fun fetchData() {
        disposable.add(dataInteractor.getEntities().observeOn(AndroidSchedulers.mainThread()).subscribe( { list ->
            Log.e("data", "list data = " + list.toString())
            if (list.isNotEmpty()) {
                state.postValue(list)
            }
        }, { error ->
            Log.e("error", error.localizedMessage.toString())
        })
        )
    }

    fun resetData() {
        disposable.add(dataInteractor.deleteAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                 fetchData()
            },{ error ->
                Log.e("error", error.localizedMessage.toString())
            }))
    }

    fun fetchDbData() {
        disposable.add(dataInteractor.getDBEntities().observeOn(AndroidSchedulers.mainThread()).subscribe({ list ->
            Log.e("data db", "list data = " + list.toString())
            if (list.isNotEmpty()) {
                state.postValue(list)
            }
        }, { error ->
            Log.e("error", error.localizedMessage.toString())
        }))
    }

    fun deleteItem(entity: Entity) {
        disposable.add(dataInteractor.deleteEntity(entity)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                   fetchDbData()
            },{ error ->
                Log.e("error", error.localizedMessage.toString())
            }))
    }
}