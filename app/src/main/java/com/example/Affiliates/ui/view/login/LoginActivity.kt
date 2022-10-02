package com.example.Affiliates.ui.view.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.databinding.ActivityLoginBinding
import com.example.Affiliates.ui.view.MainActivity

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private var pwdVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        binding.testBtn.setOnClickListener{
            // 메인 액티비티로 전환
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.loginSignupBtn.setOnClickListener {
            startActivity(Intent(this, ActivitySignUp::class.java))
        }
    }
}