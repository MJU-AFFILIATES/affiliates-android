package com.example.Affiliates.ui.view.store

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreInterface {
    @GET("/stores/getStore/{storeIdx}")
    fun getStoreFromAPI(
        @Path("storeIdx") storeIdx: Int,
    ): Call<OneStoreModel>
}