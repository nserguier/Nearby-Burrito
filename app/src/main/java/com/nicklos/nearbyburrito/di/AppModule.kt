package com.nicklos.nearbyburrito.di

import com.google.android.gms.location.places.PlaceDetectionClient
import com.nicklos.nearbyburrito.repo.DefaultPlacesRepository
import com.nicklos.nearbyburrito.repo.PlacesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module to provide dependency on an application level
 */
@Module(includes = [
    ViewModelModule::class,
    PlacesModule::class])
class AppModule {

    @Provides
    @Singleton
    fun providePlacesRepository(placeClient: PlaceDetectionClient): PlacesRepository =
            DefaultPlacesRepository(placeClient)
}