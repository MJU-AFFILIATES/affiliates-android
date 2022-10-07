package com.example.Affiliates.data

import com.google.gson.annotations.SerializedName

data class CreateReview(
    @SerializedName(value = "review") val review: String,
    @SerializedName(value = "star") val star: Int,
    @SerializedName(value = "storeIdx") val storeIdx: Int
)
