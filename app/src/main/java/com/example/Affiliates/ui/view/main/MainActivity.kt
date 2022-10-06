package com.example.Affiliates.ui.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.R
import com.example.Affiliates.databinding.ActivityMainBinding
import com.example.Affiliates.ui.view.SettingActivity
import com.example.Affiliates.ui.view.store.Store
import com.example.Affiliates.ui.view.store.StoreActivity
import com.example.Affiliates.ui.view.store.StoreModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.overlay.OverlayImage
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
    private val mapView: MapView by lazy {
        binding.mapView
    }

    private var storeIdx: Int = 0
    private var category: Int = 0
    var markerList: ArrayList<Marker> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        binding.settingIv.setOnClickListener {
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
        val cameraPosition = CameraPosition(LatLng(37.58079391,126.92466831), 16.2)
        naverMap.cameraPosition = cameraPosition

        filterCategory()


    }

    private fun getStoreListFromAPI(category: Int) {
        Log.d("STORE_RETROFIT:category", category.toString())
        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.124.107.214")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MainInterface::class.java).also {

            it.getStoreListFromAPI(category)
                .enqueue(object : Callback<StoreModel> {
                    override fun onFailure(call: Call<StoreModel>, t: Throwable) {
                        Log.d("MAIN_RETROFIT", "GET ERROR_2: " + t.message)
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
            storeIdx = store.storeIdx

            val marker = Marker()

            marker.position = LatLng(store.y.toDouble(), store.x.toDouble())
            marker.onClickListener = this

            marker.map = naverMap
            marker.tag = store.storeIdx

            when (store.category) {
                "CAFE" -> marker.icon = OverlayImage.fromResource(R.drawable.marker_cafe)
                "BAR" -> marker.icon = OverlayImage.fromResource(R.drawable.marker_bar)
                "RESTAURANT" -> marker.icon =
                    OverlayImage.fromResource(R.drawable.marker_restanrant)
                "ACTIVITY" -> marker.icon = OverlayImage.fromResource(R.drawable.marker_activity)
            }

            when (store.name) {
                "건강과 땀" -> marker.position = LatLng(37.5794081, 126.9233784)
            }

            markerList.add(marker)
            Log.d("MAIN_RETROFIT-size", markerList.size.toString())


        }
    }

    // 마커 클릭리스너
    override fun onClick(overlay: Overlay): Boolean {

        val intent = Intent(this, StoreActivity::class.java)
        intent.putExtra("storeIdx", overlay.tag.toString())
        Log.d("MAIN_RETROFIT", overlay.tag.toString())
        startActivity(intent)

        return true
    }

    private fun filterCategory() {
        getStoreListFromAPI(0)

        binding.filterAll.setOnClickListener {
            deleteMarker()
            category = 0
            getStoreListFromAPI(0)
        }

        binding.filterCafe.setOnClickListener {
            deleteMarker()
            category = 1
            getStoreListFromAPI(1)
        }

        binding.filterBar.setOnClickListener {
            deleteMarker()
            category = 2
            getStoreListFromAPI(2)
        }

        binding.filterRestaurant.setOnClickListener {
            deleteMarker()
            category = 3
            getStoreListFromAPI(3)
        }

        binding.filterActivity.setOnClickListener {
            deleteMarker()
            category = 4
            getStoreListFromAPI(4)
        }

    }

    private fun deleteMarker() {
        for (marker in markerList) {
            Log.d("MAIN_RETROFIT", marker.map.toString())
            marker.map = null
        }
    }


}


