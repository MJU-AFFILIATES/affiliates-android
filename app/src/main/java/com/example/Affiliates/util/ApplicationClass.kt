package com.example.Affiliates.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {
    companion object{
        const val X_ACCESS_TOKEN: String = "X-ACCESS-TOKEN"         // JWT Token Key
        const val X_REFRESH_TOKEN: String = "X_REFRESH_TOKEN"         // JWT Token Key
        const val TAG: String = "AFFILIATES_APP"                      // Log, SharedPreference
        const val APP_DATABASE = "$TAG-DB"

        const val BASE_URL = "http://13.124.107.214"


        lateinit var mSharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        lateinit var retrofit: Retrofit

    }

    override fun onCreate() {
        super.onCreate()

        mSharedPreferences = applicationContext.getSharedPreferences("AFFILIATES_APP", MODE_PRIVATE)
        editor = mSharedPreferences.edit()

        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
            .addInterceptor(HeaderInterceptor(getJwt().toString())) // JWT 자동 헤더 전송
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mSharedPreferences = applicationContext.getSharedPreferences(TAG, Context.MODE_PRIVATE)
    }

}