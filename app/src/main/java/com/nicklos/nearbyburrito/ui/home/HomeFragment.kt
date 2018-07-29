package com.nicklos.nearbyburrito.ui.home

import com.nicklos.nearbyburrito.ui.BaseFragment
import com.nicklos.nearbyburrito.viewmodel.HomeVM

class HomeFragment : BaseFragment<HomeVM>() {

    override fun getViewModel() = HomeVM::class.java

}