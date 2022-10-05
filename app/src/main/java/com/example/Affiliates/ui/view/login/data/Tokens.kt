package com.example.Affiliates.ui.view.login.data

import com.google.gson.annotations.SerializedName

data class Tokens(
    @SerializedName(value = "accessToken")val accessToken: String,
    @SerializedName(value = "refreshToken")val refreshToken: String,
)
