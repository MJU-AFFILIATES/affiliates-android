package com.example.Affiliates.ui.view.mypage

import retrofit2.Call
import retrofit2.http.GET

interface MypageInterface {
    @GET("/users/myPage")
    fun getUserProfile(): Call<ProfileModel>
}