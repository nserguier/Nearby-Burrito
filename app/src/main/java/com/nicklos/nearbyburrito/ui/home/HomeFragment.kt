package com.nicklos.nearbyburrito.ui.home

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.nicklos.nearbyburrito.R
import com.nicklos.nearbyburrito.databinding.FragmentHomeBinding
import com.nicklos.nearbyburrito.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * This fragment shows the list of nearby burrito places.
 */
class HomeFragment : BaseFragment() {

    @Inject
    lateinit var layoutManager: LinearLayoutManager

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
        binding.burritoRecycler.layoutManager = layoutManager
        return binding.root
    }
}
