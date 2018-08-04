package com.nicklos.nearbyburrito.ui

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import com.nicklos.nearbyburrito.viewmodel.HomeVM
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Base fragment class, for every fragment to inherit from
 * Takes care of Android Dependency Injections with Dagger2 of the fragment and viewmodel
 */
abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var homeVm: HomeVM

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        (activity as? MainActivity)?.let {
            homeVm = ViewModelProviders.of(it, viewModelFactory).get(HomeVM::class.java)
        } ?: throw Exception("BaseFragment should have MainActivity as its parent activity!")
    }
}