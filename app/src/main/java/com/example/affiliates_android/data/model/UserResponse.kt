package com.example.affiliates_android.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "code")
    val code: Int,
    @Json(name = "isSuccess")
    val isSuccess: Boolean,
    @Json(name = "message")
    val message: String,
    @Json(name = "result")
    val result: Result
)