package com.example.Affiliates.ui.view.myReview

import com.google.gson.annotations.SerializedName

data class MyReviewResponse(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : ArrayList<MyReviewResult>
)

data class MyReviewResult(
    @SerializedName("storeIdx") val storeIdx: Int,
    @SerializedName("name") val name: String,
    @SerializedName("category") val category: String,
    @SerializedName("review") val review: String,
    @SerializedName("star") val star: Int,
    @SerializedName("createdDate") val createdDate: String,
    @SerializedName("imgUrl") val imgUrl: String?,
)