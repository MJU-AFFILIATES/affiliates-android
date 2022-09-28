package com.example.affiliates_android.ui.view

import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.affiliates_android.data.repository.UserRepositoryImpl
import com.example.affiliates_android.databinding.ActivityMainBinding
import com.example.affiliates_android.databinding.FragmentHomeBinding
import com.example.affiliates_android.ui.viewmodel.UserViewModel
import com.example.affiliates_android.ui.viewmodel.UserViewModelProviderFactory
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPOIItem

import net.daum.mf.map.api.MapPoint
import java.util.*


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    // viewModel 초기화
    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // viewModel 사용
        val userRepository = UserRepositoryImpl()
        val factory = UserViewModelProviderFactory(userRepository)
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

//        val mapView = MapView(context)
//        binding.mapView.addView(mapView)

        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}