package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeStoreViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _shoeAdded = MutableLiveData<Boolean>()
    val shoeAdded: LiveData<Boolean>
        get() = _shoeAdded

    var shoeDetails = Shoe("", 0.0, "", "")

    init {
        Timber.i("init")
        _shoeList.value = mutableListOf()
        for (i in 1..3) {
            _shoeList.value!!.add(Shoe("Here name $i", 12.0, "Here Company", "Here Description"))
        }
    }

    fun addShoe() {
        Timber.i("addShoe")
        _shoeList.value!!.add(shoeDetails!!)
        _shoeAdded.value = true
    }

    fun onShoeAdded() {
        Timber.i("onShoeAdded")
        _shoeAdded.value = false
        shoeDetails = Shoe("", 0.0, "", "")
    }
}