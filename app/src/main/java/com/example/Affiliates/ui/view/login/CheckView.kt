package com.example.Affiliates.ui.view.login

interface CheckView {
    fun onCheckSuccess(code: Int, result : Boolean, item: String)
    fun onCheckFailure(code: Int, message: String)
}