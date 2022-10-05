package com.example.Affiliates.ui.view.store

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewInterface {
    @GET("/stores/review/{storeIdx}")
    fun getReviewsFromAPI(
        @Path("storeIdx") storeIdx: Int,
    ): Call<ReviewModel>


//    @POST
}