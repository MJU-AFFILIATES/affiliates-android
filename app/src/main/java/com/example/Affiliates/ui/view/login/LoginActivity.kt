package com.example.Affiliates.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.data.User
import com.example.Affiliates.databinding.ActivityLoginBinding
import com.example.Affiliates.ui.view.main.MainActivity
import com.getit.getit.ui.login.saveJwt
import com.getit.getit.ui.login.server.AuthService
import com.getit.getit.ui.login.server.Result
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity(), LoginView {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        binding.testBtn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.loginSignupBtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        login()
    }

    private fun login() {

        with(binding){
            loginLoginBtn.setOnClickListener {
                if (loginStudentIdInputEt.text.toString().isEmpty()) {
                    Toast.makeText(this@LoginActivity, "이메일을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                }
                else if (loginPasswordInputEt.text.toString().isEmpty()) {
                    Toast.makeText(this@LoginActivity, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
                else {
                    val id: String = binding.loginStudentIdInputEt.text.toString().trim()
                    val pwd: String = binding.loginPasswordInputEt.text.toString().trim()

                    val authService = AuthService()
                    authService.setLoginView(this@LoginActivity)
                    authService.login(User(id, pwd))
                }
            }
        }
    }

    override fun onLoginSuccess(code: Int, result: Result) {
        saveJwt(result.accessToken, result.refreshToken)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginFailure(Code: Int, message: String) {
        Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
    }
}