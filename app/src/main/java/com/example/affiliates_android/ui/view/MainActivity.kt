package com.example.affiliates_android.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.affiliates_android.R
import com.example.affiliates_android.data.repository.UserRepositoryImpl
import com.example.affiliates_android.databinding.ActivityMainBinding
import com.example.affiliates_android.ui.viewmodel.UserViewModel
import com.example.affiliates_android.ui.viewmodel.UserViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // viewModel 사용
        val userRepository = UserRepositoryImpl()
        val factory = UserViewModelProviderFactory(userRepository)
//        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

//        val mapView = MapView(context)
//        binding.mapView.addView(mapView)


    }


}