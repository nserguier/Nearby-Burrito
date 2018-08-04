package com.nicklos.nearbyburrito.ui.home

import android.Manifest
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.nicklos.nearbyburrito.R
import com.nicklos.nearbyburrito.databinding.FragmentHomeBinding
import com.nicklos.nearbyburrito.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * This fragment shows the list of nearby burrito places.
 */
class HomeFragment : BaseFragment() {

    companion object {
        const val LOC_REQUEST_CODE = 1
    }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeVm.onPlaceClicked.observe(this, Observer {
            Navigation.findNavController(home_root).navigate(R.id.action_home_to_map)
        })
    }

    override fun onResume() {
        super.onResume()
        startLocationChecks()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.setLifecycleOwner(this)
        binding.vm = homeVm
        binding.burritoRecycler.layoutManager = LinearLayoutManager(context)

        resetActionBar()

        return binding.root
    }

    private fun resetActionBar() {
        (activity as? AppCompatActivity)?.supportActionBar?.let {
            it.title = getString(R.string.burrito_places)
            it.setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun startLocationChecks() {
        val ctx = context ?: return

        val hasLocationPermission =
                ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

        if (!hasLocationPermission) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOC_REQUEST_CODE)
        } else {
            checkGpsEnabled()
        }
    }

    private fun checkGpsEnabled() {
        val gpsEnabled = (context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager)
                .isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!gpsEnabled) {
            showGpsDialog()
        } else {
            //Location permission is granted AND GPS is enabled
            homeVm.findNearbyBurritos()
        }
    }

    private fun showGpsDialog() {
        val ctx = context ?: return
        AlertDialog.Builder(ctx)
                .setMessage("You need to enable your GPS to find burritos near you.")
                .setPositiveButton("Settings") { dialog, _ ->
                    dialog.dismiss()
                    openSettingsGPS()
                }
                .setNegativeButton("Exit") { _, _ -> activity?.finish() }
                .show()
    }

    private fun openSettingsGPS() {
        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }
}
