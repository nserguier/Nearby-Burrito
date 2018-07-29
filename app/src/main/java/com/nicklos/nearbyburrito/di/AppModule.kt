package com.nicklos.nearbyburrito.di

import dagger.Module

/**
 * Module to provide dependency on an application level
 */
@Module(includes = [
    ViewModelModule::class,
    ExecutorModule::class])
class AppModule {
    //todo: provide repo
}