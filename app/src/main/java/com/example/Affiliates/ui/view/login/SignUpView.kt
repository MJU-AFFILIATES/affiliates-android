package com.example.Affiliates.ui.view.login

import com.getit.getit.ui.login.server.Result

interface SignUpView {
    fun onSignUpSuccess(code: Int, result: Result)
    fun onSignUpFailure(code: Int, message: String)
}