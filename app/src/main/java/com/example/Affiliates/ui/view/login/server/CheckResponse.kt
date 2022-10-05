package com.example.Affiliates.ui.view.login.server

import com.google.gson.annotations.SerializedName

data class CheckResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Boolean
)
