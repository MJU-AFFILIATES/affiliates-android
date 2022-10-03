package com.example.Affiliates.ui.view.myReview

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Affiliates.data.model.Review
import com.example.Affiliates.databinding.ActivityMyreviewBinding

class MyreviewActivity: AppCompatActivity() {
    private val binding: ActivityMyreviewBinding by lazy {
        ActivityMyreviewBinding.inflate(layoutInflater)
    }
    private var myReviewList = ArrayList<MyReviewResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.myreviewBackIv.setOnClickListener {
            finish()
        }


        setUpRecyclerView()
        getMyReview()
    }

    private fun addDummy() {
        myReviewList.apply {
            add (
                MyReviewResult(
                    1,
                    "영순이네",
                    "RESTAURANT",
                    "어쩌구 사이다..",
                    4,
                    "2022--29T16:43:51.335373",
                    "https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F5958356%3Ftimestamp%3D20220411163627",
                )
            )
            add (
                MyReviewResult(
                    2,
                    "영순이네",
                    "RESTAURANT",
                    "여기엔 리뷰 내용을 적어야되는데?",
                    4,
                    "2022-09-29T16:43:51.335373",
                    "https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F5958356%3Ftimestamp%3D20220411163627",
                )
            )
            add (
                MyReviewResult(
                    3,
                    "오브로더",
                    "CAFE",
                    "졸려 너무너무",
                    4,
                    "2022-09-29T16:43:51.335373",
                    "https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F5958356%3Ftimestamp%3D20220411163627",
                )
            )
        }
    }

    private fun setUpRecyclerView() {

        addDummy()
        val myReviewAdapter = MyReviewAdapter(myReviewList)
        binding.myreviewRecylerview.adapter = myReviewAdapter
        binding.myreviewRecylerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getMyReview() {
        val myReviewRetrofit = ApplicationClass.
    }
}