package com.example.task_planner_app.repository.remote.dto

data class UserDto(
    val id: String,
    val fullName: String,
    val email: String,
    val passwordHash: String
)