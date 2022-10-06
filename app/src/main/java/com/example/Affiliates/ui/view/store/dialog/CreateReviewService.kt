package com.example.Affiliates.ui.view.store.dialog

import android.util.Log
import com.example.Affiliates.data.User
import com.example.Affiliates.ui.view.login.SignUpView
import com.example.Affiliates.ui.view.login.server.AuthResponse
import com.example.Affiliates.ui.view.login.server.AuthRetrofitInterface
import com.example.Affiliates.ui.view.store.Review
import com.example.Affiliates.ui.view.store.ReviewInterface
import com.example.Affiliates.util.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateReviewService {
    private lateinit var reviewView: CreateReviewView

    fun setReviewView(signUpView: SignUpView){
        this.reviewView = reviewView
    }

    fun createReview(review: Review){
        val reviewService = ApplicationClass.retrofit.create(ReviewInterface::class.java)
        reviewService.createReview(review).enqueue(object: Callback<ReviewResponse> {
            override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>) {
                Log.d("createReview", response.toString())

                if (response.isSuccessful && response.code() == 200) {
                    val resp: ReviewResponse = response.body()!!

                    when(resp.code){
                        1000 -> reviewView.onReviewSuccess(resp.code, resp.result)
                        else -> reviewView.onReviewFailure(resp.code, resp.message)
                    }
                }
            }
            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                Log.d("createReview", t.message.toString())
            }
        })
        Log.d("createReview", "HELLO")
    }
}