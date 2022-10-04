package com.example.Affiliates.ui.view.myReview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MyReviewApi {
    @GET("/stores/review/user")
    fun getMyReviewList(
//        @Header("token") token: String?
    ): Call<ArrayList<MyReviewResult>>

}