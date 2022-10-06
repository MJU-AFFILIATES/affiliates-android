package com.example.Affiliates.ui.view.store.dialog

import android.util.Log
import com.example.Affiliates.data.CreateReview
import com.example.Affiliates.ui.view.store.ReviewInterface
import com.example.Affiliates.util.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateReviewService {
    private lateinit var reviewView: CreateReviewView

    fun setReviewView(reviewView: CreateReviewView){
        this.reviewView = reviewView
    }

    fun createReview(createReview: CreateReview){
        val reviewService = ApplicationClass.retrofit.create(ReviewInterface::class.java)
        reviewService.createReview(createReview).enqueue(object: Callback<CreateReviewResponse> {
            override fun onResponse(call: Call<CreateReviewResponse>, response: Response<CreateReviewResponse>) {
                Log.d("createReview", response.toString())

                if (response.isSuccessful && response.code() == 200) {
                    val resp: CreateReviewResponse = response.body()!!

                    when(resp.code){
                        1000 -> reviewView.onReviewSuccess(resp.code, resp.result)
                        else -> reviewView.onReviewFailure(resp.code, resp.message)
                    }
                }
            }
            override fun onFailure(call: Call<CreateReviewResponse>, t: Throwable) {
                Log.d("createReview", t.message.toString())
            }
        })
        Log.d("createReview", "HELLO")
    }
}