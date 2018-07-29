package com.nicklos.nearbyburrito.di

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
    abstract fun homeFragment(): HomeFragment
}