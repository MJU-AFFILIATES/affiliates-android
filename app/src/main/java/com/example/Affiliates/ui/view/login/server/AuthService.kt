package com.getit.getit.ui.login.server

import android.util.Log
import com.example.Affiliates.data.User
import com.example.Affiliates.ui.view.login.LoginView
import com.example.Affiliates.ui.view.login.SignUpView
import com.example.Affiliates.util.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {
    private lateinit var signUpView: SignUpView
    private lateinit var loginView: LoginView

    fun setSignUpView(signUpView: SignUpView){
        this.signUpView = signUpView
    }

    fun setLoginView(loginView: LoginView){
        this.loginView = loginView
    }

    fun signUp(user: User){
        val authService = ApplicationClass.retrofit.create(AuthRetrofitInterface::class.java)
        authService.signUp(user).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("signUp", response.toString())

                if (response.isSuccessful && response.code() == 200) {
                    val resp: AuthResponse = response.body()!!

                    when(resp.code){
                        1000 -> {
                            signUpView.onSignUpSuccess(resp.code, resp.result!!)
                        }
                        else -> {
                            Log.d("signUp", resp.code.toString())
                            signUpView.onSignUpFailure(resp.code)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("signUp", t.message.toString())
            }
        })
        Log.d("signUp", "HELLO")
    }

    fun login(user: User) {
        Log.d("TEST", user.toString())
        val authService = ApplicationClass.retrofit.create(AuthRetrofitInterface::class.java)
        authService.login(user).enqueue(object: Callback<AuthResponse> {
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    Log.d("login", response.body().toString())
                    val resp: AuthResponse = response.body()!!
                    Log.d("login", resp.toString())
                    when(val code = resp.code){
                        1000 -> loginView.onLoginSuccess(code, resp.result!!)
                        else -> loginView.onLoginFailure(code, resp.message)
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Log.d("login/FAILURE", t.message.toString())
                }
            })
        Log.d("login", "HELLO")
    }
}