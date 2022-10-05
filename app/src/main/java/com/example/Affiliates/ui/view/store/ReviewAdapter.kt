package com.example.Affiliates.ui.view.store

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Affiliates.databinding.ItemReviewBinding

class ReviewAdapter(val reviewList: ArrayList<Review>, val context: Context)
    : RecyclerView.Adapter<ReviewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding : ItemReviewBinding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reviewList[position], context)
    }

    override fun getItemCount(): Int = reviewList.count()

    class ViewHolder(val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review, context: Context) {
            binding.itemStoreNicknameTv.text = review.nickName
            binding.itemStoreContentTv.text = review.review
            binding.itemStoreDateTv.text = review.createdDate.substring(0,10)
            binding.itemRatingBar.rating = review.star.toFloat()

        }
    }

}