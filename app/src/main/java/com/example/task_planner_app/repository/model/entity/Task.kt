package com.example.task_planner_app.repository.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.task_planner_app.repository.remote.dto.TaskDto

@Entity
data class Task(
    @PrimaryKey
    val uid: Int,
    val id: String,
    val description: String,
    val responsible: String,
    val date: String,
    val status: String
) {
    constructor(taskDto: TaskDto): this(
        0,
        taskDto.id,
        taskDto.description,
        taskDto.responsible,
        taskDto.date,
        taskDto.status)
}