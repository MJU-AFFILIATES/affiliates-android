package com.example.Affiliates.ui.view.store

import android.net.Uri

data class OneStoreModel(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<OneStore>
)

data class OneStore(
    val address: String,
    val category: String,
    val contents: String,
    val imgUrl: String,
    val name: String,
    val storeIdx: Int,
    val x: String,
    val y: String,
    val avgStar: Double
)