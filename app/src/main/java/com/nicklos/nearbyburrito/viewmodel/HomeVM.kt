package com.nicklos.nearbyburrito.viewmodel

import android.arch.lifecycle.ViewModel
import com.google.android.gms.location.places.Place
import com.nicklos.nearbyburrito.repo.PlacesRepository
import javax.inject.Inject

class HomeVM @Inject constructor(private val repo: PlacesRepository) : ViewModel() {

    private val nearbyPlaces = mutableListOf<Place>()

    fun findNearbyBurritos() {
        repo.getCurrentPlace().addOnCompleteListener { task ->
            val likelyPlaces = task.result

            likelyPlaces.sortedByDescending { it.likelihood }
                    .forEach { nearbyPlaces.add(it.place) }

            likelyPlaces.release()
        }
    }
}