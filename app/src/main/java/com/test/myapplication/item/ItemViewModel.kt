package com.test.myapplication.item

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.test.domain.DataInteractor
import com.test.domain.entity.Entity
import com.test.myapplication.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val dataInteractor: DataInteractor) : BaseViewModel() {

    private val _state : MutableLiveData<State> = MutableLiveData(State())
    val state : MutableLiveData<State> = _state

    var item : Entity ?= null

    fun findItemByPkId(pkDeviceId : Long) {
        disposable.add(dataInteractor.getDBEntities().observeOn(AndroidSchedulers.mainThread()).subscribe( { list ->
            item = list.firstOrNull { it.pk_device == pkDeviceId }
            item?.let {
                val newState = State(loading = false, item = it, wasItemSaved = false)
                state.postValue(newState)
            }

        }, { error ->
            Log.e("error", error.localizedMessage.toString())
        }))
    }

    fun updateItemName(name : String) {
        val newItem = item?.copy(title = name)
        newItem?.let {
            disposable.add(dataInteractor.updateEntity(it)
                .observeOn(AndroidSchedulers.mainThread()).subscribe (
                {
                    val newState = _state.value?.copy(wasItemSaved = true)
                    newState?.let {
                        state.postValue(it)
                    }
                },
                {
                   error -> Log.e("error", error.localizedMessage.toString())
                }
                ))
        }
    }

}