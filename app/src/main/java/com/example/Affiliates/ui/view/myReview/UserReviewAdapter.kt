package com.example.Affiliates.ui.view.myReview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.Affiliates.databinding.ItemMyreviewBinding

class UserReviewAdapter(val userReviewList: ArrayList<UserReviewList>, val context: Context)
    : RecyclerView.Adapter<UserReviewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding : ItemMyreviewBinding = ItemMyreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userReviewList[position])
    }

    override fun getItemCount(): Int = userReviewList.count()

    class ViewHolder(val binding: ItemMyreviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(review: UserReviewList) {
            binding.itemMyReviewTitleTv.text = review.name
            binding.myreviewContentTv.text = review.review
            binding.myreviewDateTv.text = review.createdDate.substring(0,10)
            binding.itemStarLayout.rating = review.star.toFloat()

            Glide.with(binding.root.context)
                .load(review.imgUrl).apply(RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .centerCrop().into(binding.itemMyReviewImgIv)
        }
    }
}