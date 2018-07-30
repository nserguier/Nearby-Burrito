package com.nicklos.nearbyburrito.repo

import android.annotation.SuppressLint
import com.google.android.gms.location.places.PlaceDetectionClient
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse
import com.google.android.gms.tasks.Task

/**
 * Default implementation of our [PlacesRepository],
 * use it for running the app in debug or release.
 */
class DefaultPlacesRepository(private val placesClient: PlaceDetectionClient) : PlacesRepository {

    @SuppressLint("MissingPermission")
    override fun getCurrentPlace(): Task<PlaceLikelihoodBufferResponse> =
            placesClient.getCurrentPlace(null)
}