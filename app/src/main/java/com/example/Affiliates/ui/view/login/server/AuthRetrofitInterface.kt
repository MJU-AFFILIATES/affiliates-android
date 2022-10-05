package com.getit.getit.ui.login.server

import com.example.Affiliates.data.User
import com.example.Affiliates.ui.view.login.data.Tokens
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitInterface {
    @POST("/auth/sign-in")
    fun signUp(@Body user: User): Call<AuthResponse>

    @POST("/auth/login")
    fun login(@Body user: User): Call<AuthResponse>

//    @POST("/auth/reissue")
//    fun autoLogin(@Body tokens : Tokens) : Call<AuthResponse>

}