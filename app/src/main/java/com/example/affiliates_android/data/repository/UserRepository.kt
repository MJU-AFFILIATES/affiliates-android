package com.example.affiliates_android.data.repository

import com.example.affiliates_android.data.model.UserResponse
import retrofit2.Response

interface UserRepository {

    suspend fun searchUser(
        personaId: Int,
        userProfileId: String,
        personaName: String,
    ): Response<UserResponse>
}