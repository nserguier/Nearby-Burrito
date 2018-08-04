package com.nicklos.nearbyburrito.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.android.gms.location.places.Place
import com.nicklos.nearbyburrito.ActionLiveData
import com.nicklos.nearbyburrito.repo.PlacesRepository
import com.nicklos.nearbyburrito.ui.home.HomeAdapter
import javax.inject.Inject

class HomeVM @Inject constructor(private val repo: PlacesRepository) : ViewModel() {

    //Keep list of places as a live data to simplify updating on device rotation
    val places = MutableLiveData<List<Place>>()

    val progressVisibility: LiveData<Int> = Transformations.map(places) {
        if(it.isNotEmpty()) View.GONE else View.VISIBLE
    }

    var selectedPlace: Place? = null

    val adapter = HomeAdapter { place ->
        selectedPlace = place
        onPlaceClicked.call()
    }

    val onPlaceClicked = ActionLiveData<Unit>()

    fun findNearbyBurritos() {
        //If we already have results, we keep them
        if(places.value != null) return

        repo.getCurrentPlace().addOnCompleteListener { task ->
            val responses = task.result

            val newPlaces = responses.sortedByDescending { it.likelihood }.map { it.place.freeze() }
            places.value = newPlaces

            responses.release()
        }
    }

    companion object {
        /**
         * This binding adapter helps to update the adapter with the places livedata
         * without having to explicitly observe this livedata changes from the fragment.
         */
        @JvmStatic
        @BindingAdapter("homeAdapter", "places")
        fun setPlaces(recyclerView: RecyclerView, noteAdapter: HomeAdapter?, places: List<Place>?) {
            if (recyclerView.adapter == null) recyclerView.adapter = noteAdapter
            places?.let { noteAdapter?.setList(it) }
        }
    }
}