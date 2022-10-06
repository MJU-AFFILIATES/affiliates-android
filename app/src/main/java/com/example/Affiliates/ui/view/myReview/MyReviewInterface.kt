package com.example.Affiliates.ui.view.myReview

import com.example.Affiliates.data.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface MyReviewInterface {
    @GET("/stores/review/user")
    fun getMyReviewFromAPI(
        @Body user: User
    ): Call<MypageModel>
}