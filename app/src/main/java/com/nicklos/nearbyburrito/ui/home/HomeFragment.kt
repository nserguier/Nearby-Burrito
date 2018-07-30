package com.nicklos.nearbyburrito.ui.home

import android.os.Bundle
import com.nicklos.nearbyburrito.ui.BaseFragment
import com.nicklos.nearbyburrito.viewmodel.HomeVM

class HomeFragment : BaseFragment<HomeVM>() {

    override fun getViewModel() = HomeVM::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.findNearbyBurritos()
    }
}