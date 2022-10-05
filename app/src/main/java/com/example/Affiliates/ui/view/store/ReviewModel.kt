package com.example.Affiliates.ui.view.store

data class ReviewModel(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<Review>
)

data class Review (
    val reviewIdx: Int,
    val storeIdx: Int,
    val name: String,
    val category: String,
    val userIdx: Int,
    val nickName: String,
    val review: String,
    val imgUrl: String?,
    val star: Int,
    val createdDate: String,
    val avgStar: Double
)