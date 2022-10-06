package com.example.Affiliates.ui.view.store

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.Affiliates.R
import com.example.Affiliates.databinding.ActivityStoreBinding
import com.example.Affiliates.ui.view.store.dialog.RegisterReviewDialog
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.overlay.OverlayImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class StoreActivity: AppCompatActivity(), OnMapReadyCallback, Overlay.OnClickListener {
    private val binding: ActivityStoreBinding by lazy {
        ActivityStoreBinding.inflate(layoutInflater)
    }

    private lateinit var naverMap: NaverMap
    private val mapView: MapView by lazy {
        binding.storeMap
    }
    private var storeIdx: Int = 0
    private lateinit var retrofit: Retrofit


    private fun setAdapter(reviewList: ArrayList<Review>) {
        val mAdapter = ReviewAdapter(reviewList, this)
        binding.reviewRecyclerview.adapter = mAdapter
        binding.reviewRecyclerview.layoutManager = LinearLayoutManager(this)

    }

    private fun showProfileDialog() {
        val dialogView = RegisterReviewDialog(this) {

        }
        dialogView.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        retrofit = Retrofit.Builder()
            .baseUrl("http://13.124.107.214")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        binding.reviewBtn.setOnClickListener {
            // 리뷰 쓰는 창 이동
            showProfileDialog()
        }

        binding.storeCancelIv.setOnClickListener {
            finish()
        }

        val secondIntent = intent
        val _storeIdx: String? = secondIntent.getStringExtra("storeIdx")
        storeIdx = _storeIdx!!.toInt()
        Log.d("STORE_RETROFIT", storeIdx.toString())


        getStoreReviewFromAPI()
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

    }


    private fun getOneStoreFromAPI() {

        retrofit.create(StroeInterface::class.java).also {
            Log.d("STORE_RETROFIT", storeIdx.toString())
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
                            val marker = Marker()

                            if (dto.result[0].name == "건강과 땀") {
                                val cameraPosition = CameraPosition(LatLng(37.5794081, 126.9233784), 17.5)
                                naverMap.cameraPosition = cameraPosition

                                marker.position = LatLng(37.5794081, 126.9233784)
                            }
                            else {
                                val cameraPosition = CameraPosition(LatLng(dto.result[0].y.toDouble(), dto.result[0].x.toDouble()), 17.5)
                                naverMap.cameraPosition = cameraPosition

                                marker.position = LatLng(dto.result[0].y.toDouble(), dto.result[0].x.toDouble())

                                Log.d("STORE_RETROFIT_x,y", "${dto.result[0].y.toDouble()}, ${dto.result[0].x.toDouble()}" )

                            }

                            when (dto.result[0].category) {
                                "CAFE" -> marker.icon = OverlayImage.fromResource(R.drawable.marker_cafe)
                                "BAR" -> marker.icon = OverlayImage.fromResource(R.drawable.marker_bar)
                                "RESTAURANT" -> marker.icon =
                                    OverlayImage.fromResource(R.drawable.marker_restanrant)
                                "ACTIVITY" -> marker.icon = OverlayImage.fromResource(R.drawable.marker_activity)
                            }



                            marker.map = naverMap

                            Glide.with(binding.root.context)
                                .load(dto.result[0].imgUrl).apply(
                                    RequestOptions()
                                        .skipMemoryCache(true)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                )
                                .centerCrop()
                                .into(binding.storeImage)

                            binding.storeTitleTv.text = dto.result[0].name
                            binding.storeContentTv.text = dto.result[0].contents
                            binding.reviewAvgStarTv.text = dto.result[0].avgStar.toString()
                            binding.starLayout.rating = dto.result[0].avgStar.toFloat()



                        }

                    }

                })
        }
    }

    override fun onClick(overlay: Overlay): Boolean {
        TODO("Not yet implemented")
    }

    private fun getStoreReviewFromAPI() {

        retrofit.create(ReviewInterface::class.java).also {
            it.getReviewsFromAPI(storeIdx)
                .enqueue(object : Callback<ReviewModel> {

                    override fun onFailure(call: Call<ReviewModel>, t: Throwable) {
                        Log.d("REVIEW_RETROFIT", "GET ERROR_2: "+t.message)
                    }

                    override fun onResponse(
                        call: Call<ReviewModel>,
                        response: Response<ReviewModel>
                    ) {
                        if (response.isSuccessful.not()) {
                            Log.d("REVIEW_RETROFIT", "GET ERROR_1")
                            return
                        }

                        response.body()?.let { dto ->
                            Log.d("REVIEW_RETROFIT", storeIdx.toString())
                            setAdapter(dto.result)
                        }
                    }


                })

        }
    }

}



