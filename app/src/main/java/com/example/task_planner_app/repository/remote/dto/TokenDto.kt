package com.example.task_planner_app.repository.remote.dto

import java.util.*

data class TokenDto(
    val token: String,
    val expirationTime: Date
)