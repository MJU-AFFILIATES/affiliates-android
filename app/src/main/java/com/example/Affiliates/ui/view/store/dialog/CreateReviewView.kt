package com.example.Affiliates.ui.view.store.dialog

interface CreateReviewView {
    fun onReviewSuccess(code: Int, result : String)
    fun onReviewFailure(code: Int, message: String)
}