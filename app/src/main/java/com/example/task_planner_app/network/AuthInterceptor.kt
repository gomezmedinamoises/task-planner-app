package com.example.task_planner_app.network

import com.example.task_planner_app.storage.Storage
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val storage: Storage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = storage.getToken()
        if (token.isNotEmpty()) {
            request.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(request.build())
    }
}