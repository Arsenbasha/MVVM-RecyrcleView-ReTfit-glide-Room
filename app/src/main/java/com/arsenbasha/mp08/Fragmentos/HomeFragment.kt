package com.arsenbasha.mp08.Fragmentos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arsenbasha.mp08.Adapter.MarcasAdapter
import com.arsenbasha.mp08.R
import com.arsenbasha.mp08.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.bind(root)
        binding.progreso.isGone=false

        homeViewModel.callGetMarcas(root, context,false)
        val refrescar = binding.refescar
        refrescar.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            homeViewModel.callGetMarcas(root, context,true)
            refrescar.setRefreshing(false);
        })

        homeViewModel.findAllMarcas().observe(viewLifecycleOwner, Observer { v ->
            binding.recyclerView.adapter = MarcasAdapter(v)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        })
        return root
    }

}


