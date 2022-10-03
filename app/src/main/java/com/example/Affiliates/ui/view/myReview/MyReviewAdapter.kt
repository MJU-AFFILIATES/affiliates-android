package com.example.Affiliates.ui.view.myReview

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Affiliates.databinding.ItemMyreviewBinding

class MyReviewAdapter(private val myReviewList: ArrayList<MyReviewResult>): RecyclerView.Adapter<MyReviewAdapter.ViewHolder>() {
    lateinit var bitmap: Bitmap

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemMyreviewBinding = ItemMyreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myReviewList[position])
    }

    override fun getItemCount(): Int = myReviewList.size

    inner class ViewHolder(val binding: ItemMyreviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(review: MyReviewResult) {
            binding.itemMyReviewTitleTv.text = review.name
            binding.myreviewContentTv.text = review.review
            binding.myreviewDateTv.text = review.createdDate.substring(0,10)

        }
    }






}