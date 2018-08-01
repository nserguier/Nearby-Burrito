package com.nicklos.nearbyburrito.ui.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicklos.nearbyburrito.R
import com.nicklos.nearbyburrito.databinding.FragmentHomeBinding
import com.nicklos.nearbyburrito.ui.BaseFragment
import com.nicklos.nearbyburrito.viewmodel.HomeVM
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeVM>() {

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    override fun getViewModel() = HomeVM::class.java

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.setLifecycleOwner(this)
        binding.vm = viewModel
        binding.burritoRecycler.layoutManager = layoutManager
        return binding.root
    }
}
