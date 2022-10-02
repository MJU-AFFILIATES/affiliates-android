package com.example.Affiliates.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.databinding.ActivitySettingBinding

class SettingActivity: AppCompatActivity() {
    private val binding: ActivitySettingBinding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}