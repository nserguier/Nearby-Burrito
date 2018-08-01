package com.nicklos.nearbyburrito.ui.map

import com.nicklos.nearbyburrito.ui.BaseFragment
import com.nicklos.nearbyburrito.viewmodel.MapVM

/**
 * This fragment shows a google map view with the location
 * of the selected burrito place.
 */
class MapFragment : BaseFragment<MapVM>() {

    override fun getViewModel() = MapVM::class.java
}