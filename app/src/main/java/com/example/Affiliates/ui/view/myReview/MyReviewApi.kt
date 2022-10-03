package com.example.Affiliates.ui.view.myReview

import retrofit2.Call
import retrofit2.http.GET

interface MyReviewApi {
    @GET("/stores/review/user")
    fun getMyReview(): Call<MyReviewResponse>
}