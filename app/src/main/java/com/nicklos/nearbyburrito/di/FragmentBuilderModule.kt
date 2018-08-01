package com.nicklos.nearbyburrito.di

import com.google.android.gms.maps.MapFragment
import com.nicklos.nearbyburrito.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module to help Dagger know about the fragments to inject with AndroidInjector
 * Place all fragments here
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun provideMapFragment(): MapFragment
}