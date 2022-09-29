package com.example.affiliates_android.ui.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.affiliates_android.data.User
import com.example.affiliates_android.databinding.ActivitySignupBinding

class ActivitySignUp: AppCompatActivity() {
    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signupSignupBtn.setOnClickListener {
            signUp()
        }
    }

//    private fun getUser(): User {
//        val studentId = binding.signupStudentIdEt.text.toString()
//        val password = binding.signupPasswordEt.text.toString()
//        val nickname = binding.signupNicknameEt.text.toString()
//
//        return User(studentId, password, nickname)
//    }

    private fun signUp() {

    }
}