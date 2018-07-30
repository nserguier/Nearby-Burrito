package com.nicklos.nearbyburrito.repo

import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse
import com.google.android.gms.tasks.Task

/**
 * This class is to abstract interactions from our view models with the data
 * so that it can easily be mocked during unit tests.
 */
interface PlacesRepository {

    fun getCurrentPlace(): Task<PlaceLikelihoodBufferResponse>
}