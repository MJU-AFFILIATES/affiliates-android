package com.example.Affiliates.ui.view.myReview

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Affiliates.data.model.Review
import com.example.Affiliates.databinding.ActivityMyreviewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MyreviewActivity: AppCompatActivity() {
    private val binding: ActivityMyreviewBinding by lazy {
        ActivityMyreviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.myreviewBackIv.setOnClickListener {
            finish()
        }

//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://13.124.107.214")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()





    }


}