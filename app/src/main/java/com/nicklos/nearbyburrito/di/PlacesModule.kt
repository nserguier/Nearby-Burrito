package com.nicklos.nearbyburrito.di

import android.app.Application
import com.google.android.gms.location.places.GeoDataClient
import com.google.android.gms.location.places.PlaceDetectionClient
import com.google.android.gms.location.places.Places
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides the app with the Places API main components
 */
@Module
class PlacesModule {

    @Provides
    @Singleton
    fun provideGeoDataClient(app: Application): GeoDataClient = Places.getGeoDataClient(app)

    @Provides
    @Singleton
    fun providePlaceDetectionClient(app: Application): PlaceDetectionClient = Places.getPlaceDetectionClient(app)
}
