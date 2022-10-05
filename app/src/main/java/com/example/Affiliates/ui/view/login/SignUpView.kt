package com.example.Affiliates.ui.view.login


import com.example.Affiliates.ui.view.login.server.Result

interface SignUpView {
    fun onSignUpSuccess(code: Int, result: Result)
    fun onSignUpFailure(code: Int, message: String)
}