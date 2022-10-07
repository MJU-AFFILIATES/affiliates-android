package com.example.Affiliates.ui.view.myReview

data class UserReviewModel(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<UserReviewList>
)

data class UserReviewList (
    val storeIdx: Int,
    val name: String,
    val category: String,
    val imgUrl: String,
    val nickName: String,
    val review: String,
    val star: Int,
    val createdDate: String
)