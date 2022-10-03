package com.example.Affiliates.data.model

import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class Review(
    val storeIdx: Int,
    val name: String,
    val category: String,
    val nickName: String,
    val review: String,
    val star: Int,
    val createdDate: String,
    val img: String?
)
