package com.example.Affiliates.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName(value = "studentNum") val studentNum: String,
    @SerializedName(value = "password") val password: String,
    @SerializedName(value = "nickName") val nickName: String
)
