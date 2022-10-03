package com.example.Affiliates.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.Affiliates.data.model.Review
import com.example.Affiliates.databinding.ItemMyreviewBinding

class MyReviewViewHolder(
    private val binding: ItemMyreviewBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(review: Review) {
        val storeIdx = review.storeIdx
        val name = review.name
        val category = review.category
        val nickName = review.nickName
        val content = review.review
        val star = review.star
        val createdDate = review.createdDate.substring(0, 10)

        itemView.apply {
            binding.itemMyReviewIv.load(review.img)
            binding.itemMyReviewTitleTv.text = review.name
            binding.myreviewContentTv.text = review.review
            binding.myreviewDateTv.text = review.createdDate.substring(0,10)
        }
    }
}