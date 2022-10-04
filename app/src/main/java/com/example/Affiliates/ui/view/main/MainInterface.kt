package com.example.Affiliates.ui.view.main

import com.example.Affiliates.ui.view.store.StoreModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MainInterface {
    @GET("stores/storeList/{category}")
    fun getStoreListFromAPI(
        @Path("category") category: Int,
    ): Call<StoreModel>
}