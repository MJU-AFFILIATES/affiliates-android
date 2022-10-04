package com.example.Affiliates.ui.view.store

import android.accessibilityservice.GestureDescription.StrokeDescription

data class StoreModel(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<Store>
)

data class Store(
    val storeIdx: Int,
    val name: String,
    val contents: String,
    val category: String,
    val address: String,
    val x: String,
    val y: String
)
