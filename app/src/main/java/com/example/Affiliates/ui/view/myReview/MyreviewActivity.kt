package com.example.Affiliates.ui.view.myReview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Affiliates.databinding.ActivityMyreviewBinding
import com.example.Affiliates.ui.view.store.ReviewInterface
import com.example.Affiliates.util.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyreviewActivity: AppCompatActivity() {
    private val binding: ActivityMyreviewBinding by lazy {
        ActivityMyreviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getReviewListFromAPI()

        binding.myreviewBackIv.setOnClickListener {
            finish()
        }
    }

    private fun getReviewListFromAPI() {
        ApplicationClass.retrofit.create(ReviewInterface::class.java).also {
            it.getUserReviewsFromAPI().enqueue(object : Callback<UserReviewModel> {
                override fun onFailure(call: Call<UserReviewModel>, t: Throwable) {
                    Log.d("getReviewListFromAPI", "GET ERROR_2: " + t.message)
                }
                override fun onResponse(call: Call<UserReviewModel>, response: Response<UserReviewModel>) {
                    if (response.isSuccessful.not()) {
                        Log.d("getReviewListFromAPI", "GET ERROR_1")
                        return
                    }
                    response.body()?.let { dto ->
                        Log.d("getReviewListFromAPI", "GET SUCCESS")
                        setAdapter(dto.result)
                    }
                }
            })
        }
    }

    private fun setAdapter(UserReviewList: ArrayList<UserReviewList>) {
        val mAdapter = UserReviewAdapter(UserReviewList, this)
        binding.myreviewRecylerview.adapter = mAdapter
        binding.myreviewRecylerview.layoutManager = LinearLayoutManager(this)

    }
}