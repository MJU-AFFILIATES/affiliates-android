package com.example.affiliates_android.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.affiliates_android.R
import com.example.affiliates_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setHomeView()

        binding.settingBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, SettingFragment())
                .commit()
        }
    }

    private fun setHomeView() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, HomeFragment())
            .commit()
    }


}