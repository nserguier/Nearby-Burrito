package com.nicklos.nearbyburrito.ui.map

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.nicklos.nearbyburrito.R
import com.nicklos.nearbyburrito.databinding.FragmentMapBinding
import com.nicklos.nearbyburrito.ui.BaseFragment
import com.nicklos.nearbyburrito.viewmodel.MapVM


/**
 * This fragment shows a google map view with the location
 * of the selected burrito place.
 */
class MapFragment : BaseFragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    private lateinit var mapVm: MapVM
    private lateinit var binding: FragmentMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mapVm = ViewModelProviders.of(this, viewModelFactory).get(MapVM::class.java).apply {
            start(homeVm)
        }

        //Set action bar title
        (activity as? AppCompatActivity)?.supportActionBar?.title = homeVm.selectedPlace?.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        binding.setLifecycleOwner(this)
        binding.vm = mapVm

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        //Show marker and center map at position
        homeVm.selectedPlace?.let {
            val position = it.latLng
            map.addMarker(MarkerOptions().position(position).title(it.name.toString()))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 16f))
        }
    }
}