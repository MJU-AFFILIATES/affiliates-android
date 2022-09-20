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

        setupBottomNavigationView()
    }

    // bottomNavigationView
    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_add_or_delete -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, AddOrDeleteFragment())
                        .commit()
                    true
                }
                R.id.fragment_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, HomeFragment())
                        .commit()
                    true
                }
                R.id.fragment_setting -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, SettingFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}