package com.example.affiliates_android.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @field:Json(name = "personaId")
    val personaId: Int,
    @field:Json(name = "personaName")
    val personaName: String,
    @field:Json(name = "userProfileName")
    val userProfileName: String
)