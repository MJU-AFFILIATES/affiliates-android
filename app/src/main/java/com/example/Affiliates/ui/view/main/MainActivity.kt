package com.example.Affiliates.ui.view.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.databinding.ActivityMainBinding
import com.example.Affiliates.ui.view.SettingActivity
import com.example.Affiliates.ui.view.store.Store
import com.example.Affiliates.ui.view.store.StoreActivity
import com.example.Affiliates.ui.view.store.StoreModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
        mapView.getMapAsync(this) // 이거 왜 호출이 안되지?

        binding.settingIv.setOnClickListener{
            startActivity(Intent(this, SettingActivity::class.java))
        }

        binding.locTv.setOnClickListener {
            startActivity(Intent(this, StoreActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    @UiThread
    override fun onMapReady(map: NaverMap) {
        Log.d("MAIN_RETROFIT", "지도준비")
        naverMap = map

        // 줌 범위 설정
        naverMap.maxZoom = 18.0
        naverMap.minZoom = 10.0

        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, true)

        // 지도의 중심점 이동
        val cameraPosition = CameraPosition(LatLng(37.580153641583045, 126.9228129200726), 15.5)
        naverMap.cameraPosition = cameraPosition

        getStoreListFromAPI()

    }

    private fun getStoreListFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.124.107.214")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MainInterface::class.java).also {

            it.getStoreListFromAPI(0)
                .enqueue(object : Callback<StoreModel> {
                    override fun onFailure(call: Call<StoreModel>, t: Throwable) {
                        Log.d("MAIN_RETROFIT", "GET ERROR_2: "+t.message)
                    }
                    override fun onResponse(
                        call: Call<StoreModel>,
                        response: Response<StoreModel>
                    ) {
                        if (response.isSuccessful.not()) {
                            Log.d("MAIN_RETROFIT", "GET ERROR_1")
                            return
                        }

                        response.body()?.let { dto ->
                            Log.d("MAIN_RETROFIT", "GET SUCCESS-마커찍자!")
                            updateMarker(dto.result)
                        }
                    }
                })
        }
    }

    private fun updateMarker(stores: List<Store>) {
        Log.d("MAIN_RETROFIT", "GET SUCCESS_MARKER")


        stores.forEach { store ->

            val marker = Marker()
            marker.position = LatLng(store.y.toDouble(), store.x.toDouble())
            marker.onClickListener = this

            marker.map = naverMap
            marker.tag = store.name

            when (store.category) {
                "CAFE" -> marker.iconTintColor = Color.RED
                "BAR" ->  marker.iconTintColor = Color.YELLOW
                "RESTAURANT" ->  marker.iconTintColor = Color.GREEN
                "ACTIVITY" ->  marker.iconTintColor = Color.BLUE
                else ->  marker.iconTintColor = Color.BLACK
            }
            marker.icon = MarkerIcons.BLACK
        }
    }

    // 마커 클릭리스너너
    override fun onClick(overlay: Overlay): Boolean {
//        val selectedModel =
       return true
    }




}
