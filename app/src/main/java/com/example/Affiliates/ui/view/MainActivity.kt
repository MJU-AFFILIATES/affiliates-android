package com.example.Affiliates.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.R
import com.example.Affiliates.data.repository.UserRepositoryImpl
import com.example.Affiliates.databinding.ActivityMainBinding
import com.example.Affiliates.ui.viewmodel.UserViewModelProviderFactory
import com.google.android.gms.maps.MapFragment
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapSdk
import com.naver.maps.map.NaverMapSdk.NaverCloudPlatformClient
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource

//import net.daum.mf.map.api.MapView

class MainActivity : AppCompatActivity(), OnMapReadyCallback, Overlay.OnClickListener {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private val mapView: MapView by lazy {
        binding.mapView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync(this)

        // viewModel 사용
        val userRepository = UserRepositoryImpl()
        val factory = UserViewModelProviderFactory(userRepository)
//        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        binding.settingIv.setOnClickListener{
            startActivity(Intent(this, SettingActivity::class.java))
        }


    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.580153641583045, 126.9228129200726))
        naverMap.moveCamera(cameraUpdate)

        val uiSetting = naverMap.uiSettings
        uiSetting.isLocationButtonEnabled = false


    }

    override fun onClick(p0: Overlay): Boolean {
        TODO("Not yet implemented")
    }




}
