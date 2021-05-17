package com.example.task_planner_app.repository.dto

import java.util.*

data class TokenDto(
    val token: String,
    val expirationTime: Date
)