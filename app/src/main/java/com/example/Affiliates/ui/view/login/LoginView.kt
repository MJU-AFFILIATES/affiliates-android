package com.example.Affiliates.ui.view.login

import com.getit.getit.ui.login.server.Result

interface LoginView {
    fun onLoginSuccess(code: Int, result : Result)
    fun onLoginFailure(code: Int, message: String)
}