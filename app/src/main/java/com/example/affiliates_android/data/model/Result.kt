package com.example.affiliates_android.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "personaId")
    val personaId: Int,
    @Json(name = "personaName")
    val personaName: String,
    @Json(name = "userProfileName")
    val userProfileName: String
)