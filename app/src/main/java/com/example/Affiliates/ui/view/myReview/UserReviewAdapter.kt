package com.example.Affiliates.ui.view.myReview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Affiliates.databinding.ItemMyreviewBinding

class UserReviewAdapter(val userReviewList: ArrayList<UserReviewList>, val context: Context)
    : RecyclerView.Adapter<UserReviewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding : ItemMyreviewBinding = ItemMyreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userReviewList[position], context)
    }

    override fun getItemCount(): Int = userReviewList.count()

    class ViewHolder(val binding: ItemMyreviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(review: UserReviewList, context: Context) {
        }
    }
}