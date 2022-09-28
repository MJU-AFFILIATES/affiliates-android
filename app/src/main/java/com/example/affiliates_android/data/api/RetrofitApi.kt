package com.example.affiliates_android.data.api

import com.example.affiliates_android.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("/users/persona?profileId=1")
    suspend fun searchUser (
        @Query("personaId") personaId: Int,
        @Query("userProfileName") userProfileName: String,
        @Query("personaName") personaName: String,
    ): Response<UserResponse>
}