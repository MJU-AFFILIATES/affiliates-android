package com.example.affiliates_android.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.affiliates_android.databinding.ActivitySettingBinding

class SettingActivity: AppCompatActivity() {
    private val binding: ActivitySettingBinding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}