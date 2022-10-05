package com.example.Affiliates.util

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor constructor(private val token:String):Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = getJwt().toString()
        val token = "Bearer $accessToken"
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization",token)
            .build()

        return chain.proceed(newRequest)
    }
}