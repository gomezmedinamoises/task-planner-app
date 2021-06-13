package com.example.task_planner_app.repository.remote.dto

data class TaskDto(
    val id: String,
    var description: String,
    var responsible: String,
    var date: String,
    var status: String
)