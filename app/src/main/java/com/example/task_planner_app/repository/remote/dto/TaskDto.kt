package com.example.task_planner_app.repository.remote.dto

data class TaskDto(
    val id: String,
    val description: String,
    val responsible: String,
    val date: String,
    val status: String
)