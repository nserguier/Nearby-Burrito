package com.nicklos.nearbyburrito.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class MapVM @Inject constructor(): ViewModel() {

    val placeAddress = MutableLiveData<String>()
    val placePricing = MutableLiveData<Int>()
    val placeRating = MutableLiveData<Float>()

    fun start(homeVM: HomeVM) {
        homeVM.selectedPlace?.let{
            placeAddress.value = it.address.toString()
            placePricing.value = it.priceLevel
            placeRating.value = it.rating
        }
    }
}