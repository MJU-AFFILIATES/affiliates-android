package com.example.Affiliates.ui.view.myReview

import com.example.Affiliates.ui.view.store.Review

data class MypageModel(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<MyReview>
)

data class MyReview (
    val category: String,
    val createdDate: String,
    val imgUrl: String,
    val nickName: String,
    val review: String,
    val star: Int,
    val storeIdx: Int,
    val name: String
)

