package com.nicklos.nearbyburrito.di

import com.nicklos.nearbyburrito.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module to help Dagger know about the activities to inject with AndroidInjector
 * Place all activities here, add the FragmentBuilderModule if the activity will be creating fragments
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun homeActivity(): MainActivity
}