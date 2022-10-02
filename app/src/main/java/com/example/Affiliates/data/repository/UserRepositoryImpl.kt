package com.example.Affiliates.data.repository

import com.example.Affiliates.data.api.RetrofitInstance.api
import com.example.Affiliates.data.model.UserResponse
import retrofit2.Response

class UserRepositoryImpl: UserRepository {
    override suspend fun searchUser(
        personaId: Int,
        userProfileId: String,
        personaName: String
    ): Response<UserResponse> {
        return api.searchUser(personaId, userProfileId, personaName)
    }
}