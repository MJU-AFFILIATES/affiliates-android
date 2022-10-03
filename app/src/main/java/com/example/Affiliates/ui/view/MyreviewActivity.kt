package com.example.Affiliates.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.databinding.ActivityMyreviewBinding

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
    }
}