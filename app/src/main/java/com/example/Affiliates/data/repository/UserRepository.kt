package com.example.Affiliates.data.repository

import com.example.Affiliates.data.model.UserResponse
import retrofit2.Response

interface UserRepository {

    suspend fun searchUser(
        personaId: Int,
        userProfileId: String,
        personaName: String,
    ): Response<UserResponse>
}