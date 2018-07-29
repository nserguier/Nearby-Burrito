package com.nicklos.nearbyburrito.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nicklos.nearbyburrito.viewmodel.HomeVM
import com.nicklos.nearbyburrito.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Module to provide dependencies to the viewmodel classes
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeVM::class)
    internal abstract fun bindHomeVm(homeVm: HomeVM): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}