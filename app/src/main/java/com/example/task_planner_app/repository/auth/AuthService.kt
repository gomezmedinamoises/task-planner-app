package com.example.task_planner_app.repository.auth

import com.example.task_planner_app.repository.dto.LoginDto
import com.example.task_planner_app.repository.dto.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth")
    suspend fun auth(@Body loginDto: LoginDto): Response<TokenDto>
}