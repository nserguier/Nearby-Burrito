package com.nicklos.nearbyburrito.ui

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation
import com.nicklos.nearbyburrito.R
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        const val REQUEST_CODE = 1
    }

    @Inject
    lateinit var fragmentAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = fragmentAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val hasLocationPermission =
                ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

        if (!hasLocationPermission) {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), REQUEST_CODE)
            //todo react on permission granted, show nothing otherwise
        }
    }

    override fun onSupportNavigateUp() =
            Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
}