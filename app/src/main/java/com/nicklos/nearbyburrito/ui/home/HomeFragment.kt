package com.nicklos.nearbyburrito.ui.home

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
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

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeVm.onPlaceClicked.observe(this, Observer {
            Navigation.findNavController(home_root).navigate(R.id.action_home_to_map)
        })
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
}
