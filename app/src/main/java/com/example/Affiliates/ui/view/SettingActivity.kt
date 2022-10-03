package com.example.Affiliates.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.databinding.ActivitySettingBinding
import com.example.Affiliates.ui.view.myReview.MyreviewActivity

class SettingActivity: AppCompatActivity() {
    private val binding: ActivitySettingBinding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.settingReviewTv.setOnClickListener {
            startActivity(Intent(this, MyreviewActivity::class.java))
        }

        binding.settingBackIv.setOnClickListener {
            finish()
        }

    }
}