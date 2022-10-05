package com.example.Affiliates.ui.view.login

interface CheckView {
    fun onCheckSuccess(code: Int, result : Boolean)
    fun onCheckFailure(code: Int, message: String)
}