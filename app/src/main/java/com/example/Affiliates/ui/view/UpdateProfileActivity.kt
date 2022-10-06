package com.example.Affiliates.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.databinding.ActivityUpdateProfileBinding

class UpdateProfileActivity: AppCompatActivity() {
    private val binding: ActivityUpdateProfileBinding by lazy {
        ActivityUpdateProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}