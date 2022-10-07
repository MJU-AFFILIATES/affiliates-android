package com.example.Affiliates.ui.view.mypage

data class ProfileModel(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: Profile
)

data class Profile(
    val studentNum: Int,
    val nickName: String
)