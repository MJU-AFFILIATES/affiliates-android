package com.example.Affiliates.ui.view.login.server

import android.util.Log
import com.example.Affiliates.data.User
import com.example.Affiliates.ui.view.login.CheckView
import com.example.Affiliates.ui.view.login.LoginView
import com.example.Affiliates.ui.view.login.SignUpView
import com.example.Affiliates.ui.view.login.data.Tokens
import com.example.Affiliates.util.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {
    private lateinit var signUpView: SignUpView
    private lateinit var loginView: LoginView
    private lateinit var checkView: CheckView

    fun setSignUpView(signUpView: SignUpView){
        this.signUpView = signUpView
    }

    fun setLoginView(loginView: LoginView){
        this.loginView = loginView
    }

    fun setCheckView(checkView: CheckView){
        this.checkView = checkView
    }

    fun signUp(user: User){
        val authService = ApplicationClass.retrofit.create(AuthRetrofitInterface::class.java)
        authService.signUp(user).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("signUp", response.toString())

                if (response.isSuccessful && response.code() == 200) {
                    val resp: AuthResponse = response.body()!!

                    when(resp.code){
                        1000 -> signUpView.onSignUpSuccess(resp.code, resp.result!!)
                        else -> signUpView.onSignUpFailure(resp.code, resp.message)
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
        val authService = ApplicationClass.retrofit.create(AuthRetrofitInterface::class.java)
        authService.login(user).enqueue(object: Callback<AuthResponse> {
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    if (response.isSuccessful && response.code() == 200) {
                        val resp: AuthResponse = response.body()!!
                        when(val code = resp.code){
                            1000 -> loginView.onLoginSuccess(code, resp.result!!)
                            else -> loginView.onLoginFailure(code, resp.message)
                        }
                    }
                }
                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Log.d("login/FAILURE", t.message.toString())
                }
            })
        Log.d("login", "HELLO")
    }

    fun checkStudent(studentNum: String) {
        val authService = ApplicationClass.retrofit.create(AuthRetrofitInterface::class.java)
        authService.checkStudent(studentNum).enqueue(object: Callback<CheckResponse> {
            override fun onResponse(call: Call<CheckResponse>, response: Response<CheckResponse>) {
                if (response.isSuccessful && response.code() == 200) {
                    val resp: CheckResponse = response.body()!!
                    Log.d("checkStudent", resp.toString())
                    when(val code = resp.code){
                        1000 -> checkView.onCheckSuccess(code, resp.result, "studentNum")
                        else -> checkView.onCheckFailure(code, resp.message)
                    }
                }
            }
            override fun onFailure(call: Call<CheckResponse>, t: Throwable) {
                Log.d("checkStudent/FAILURE", t.message.toString())
            }
        })
        Log.d("checkStudent", "HELLO")
    }

    fun checkNickname(nickName: String) {
        val authService = ApplicationClass.retrofit.create(AuthRetrofitInterface::class.java)
        authService.checkNickName(nickName).enqueue(object: Callback<CheckResponse> {
            override fun onResponse(call: Call<CheckResponse>, response: Response<CheckResponse>) {
                if (response.isSuccessful && response.code() == 200) {
                    val resp: CheckResponse = response.body()!!
                    Log.d("checkNickName", resp.toString())
                    when(val code = resp.code){
                        1000 -> checkView.onCheckSuccess(code, resp.result, "nickName")
                        else -> checkView.onCheckFailure(code, resp.message)
                    }
                }
            }
            override fun onFailure(call: Call<CheckResponse>, t: Throwable) {
                Log.d("checkNickname/FAILURE", t.message.toString())
            }
        })
        Log.d("checkNickname", "HELLO")
    }

    fun autoLogin(tokens: Tokens){
        val authService = ApplicationClass.retrofit.create(AuthRetrofitInterface::class.java)
        authService.autoLogin(tokens)
            .enqueue(object: Callback<AuthResponse> {
                override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                    Log.d("테스트", response.toString());
                    if(response.body() == null){
                        loginView.onAutoLoginFailure()
                    }else {

                        val resp: AuthResponse = response.body()!!
                        when (val code = resp.code) {
                            1000 -> loginView.onLoginSuccess(code, resp.result!!)
                            else -> loginView.onAutoLoginFailure()
                        }
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Log.d("LOGIN/FAILURE", t.message.toString())
                }

            })
    }
}