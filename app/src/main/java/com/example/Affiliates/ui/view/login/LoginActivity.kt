package com.example.Affiliates.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Affiliates.data.User
import com.example.Affiliates.databinding.ActivityLoginBinding
import com.example.Affiliates.ui.view.login.data.Tokens
import com.example.Affiliates.ui.view.login.server.AuthService
import com.example.Affiliates.ui.view.main.MainActivity
import com.example.Affiliates.util.saveJwt
import com.example.Affiliates.ui.view.login.server.Result
import com.example.Affiliates.util.ApplicationClass
import com.example.Affiliates.util.getJwt
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(), LoginView {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
//        binding.testBtn.setOnClickListener{
//            startActivity(Intent(this, MainActivity::class.java))
//        }

        binding.loginSignupBtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        if(getJwt() != null){
            autoLoginMedium()
        }
        login()
    }

    fun autoLoginMedium(){
        val authService = AuthService()
        authService.setLoginView(this)

        authService.autoLogin(
            Tokens(getJwt().toString(),
            ApplicationClass.mSharedPreferences.getString(ApplicationClass.X_REFRESH_TOKEN, null).toString())
        )
    }

    private fun login() {

        with(binding){
            loginLoginBtn.setOnClickListener {
                if (loginStudentIdInputEt.text.toString().isEmpty()) {
                    Snackbar.make(binding.root, "학번을 입력해 주세요.", Snackbar.LENGTH_SHORT).show()
                }
                else if (loginPasswordInputEt.text.toString().isEmpty()) {
                    Snackbar.make(binding.root, "비밀번호를 입력해주세요.", Snackbar.LENGTH_SHORT).show()
                }
                else {
                    val id: String = binding.loginStudentIdInputEt.text.toString().trim()
                    val pwd: String = binding.loginPasswordInputEt.text.toString().trim()

                    val authService = AuthService()
                    authService.setLoginView(this@LoginActivity)
                    authService.login(User(id, pwd, ""))
                }
            }
        }
    }

    override fun onLoginSuccess(code: Int, result: Result) {
        saveJwt(result.accessToken, result.refreshToken)
        Toast.makeText(this, "어서오세요 :)", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginFailure(Code: Int, message: String) {
        Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
    }

    override fun onAutoLoginFailure() {
        Log.d("onAutoLoginFailure", "실패...")
    }
}