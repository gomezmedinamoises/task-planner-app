package com.example.task_planner_app.repository.repo

import com.example.task_planner_app.repository.model.dao.TaskDao
import com.example.task_planner_app.repository.remote.task.TaskService
import javax.inject.Inject

class TaskRepository @Inject constructor(
    val taskService: TaskService,
    val taskDao: TaskDao
)