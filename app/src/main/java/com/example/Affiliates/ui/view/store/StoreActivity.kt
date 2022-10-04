package com.example.Affiliates.ui.view.store

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.databinding.ActivityStoreBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreActivity: AppCompatActivity(), OnMapReadyCallback, Overlay.OnClickListener {
    private val binding: ActivityStoreBinding by lazy {
        ActivityStoreBinding.inflate(layoutInflater)
    }

    private lateinit var naverMap: NaverMap
    private val mapView: MapView by lazy {
        binding.storeMap
    }
    private var storeIdx: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        binding.storeReviewIv.setOnClickListener {
            // 리뷰 쓰는 창 이동
        }

        binding.storeCancelIv.setOnClickListener {
            finish()
        }

        val secondIntent = intent
        storeIdx = secondIntent.getIntExtra("storeIdx", 0)

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

    override fun onMapReady(map: NaverMap) {
        Log.d("STORE_RETROFIT", "지도준비")
        naverMap = map

        getOneStoreFromAPI()

        // 값 받아와서 중심점 설정
//        val cameraPosition = CameraPosition(LatLng())
    }

    private fun getOneStoreFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.124.107.214")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(StroeInterface::class.java).also {
           // 일단 StoreIdx 1로 했는데 마커에서 받아와야함
            it.getStoreFromAPI(storeIdx)
                .enqueue(object : Callback<OneStoreModel> {

                    override fun onFailure(call: Call<OneStoreModel>, t: Throwable) {
                        Log.d("STORE_RETROFIT", "GET ERROR_2: "+t.message)
                    }

                    @SuppressLint("LongLogTag")
                    override fun onResponse(
                        call: Call<OneStoreModel>,
                        response: Response<OneStoreModel>
                    ) {
                        if (response.isSuccessful.not()) {
                            Log.d("STORE_RETROFIT", "GET ERROR_1")
                            return
                        }

                        response.body()?.let { dto ->
                            Log.d("STORE_RETROFIT", storeIdx.toString())
                            binding.storeTitleTv.text = dto.result[0].name
                            binding.storeImageIv.setImageURI(dto.result[0].imgUrl)!!

                        }

                    }

                })
            }
        }

    override fun onClick(overlay: Overlay): Boolean {
        TODO("Not yet implemented")
    }
}



