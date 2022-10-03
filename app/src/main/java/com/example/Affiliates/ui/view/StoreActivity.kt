package com.example.Affiliates.ui.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.databinding.ActivityStoreBinding
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap

class StoreActivity: AppCompatActivity() {
    private val binding: ActivityStoreBinding by lazy {
        ActivityStoreBinding.inflate(layoutInflater)
    }

    private lateinit var naverMap: NaverMap

    private val mapView: MapView by lazy {
        binding.storeMap
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mapView.onCreate(savedInstanceState)

        binding.storeReviewIv.setOnClickListener {
            // 리뷰 쓰는 창
        }




    }
}