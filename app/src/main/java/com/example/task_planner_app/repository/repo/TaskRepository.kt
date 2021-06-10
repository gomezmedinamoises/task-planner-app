package com.example.task_planner_app.repository.repo

import com.example.task_planner_app.repository.model.dao.TaskDao
import com.example.task_planner_app.repository.model.entity.Task
import com.example.task_planner_app.repository.remote.task.TaskService
import javax.inject.Inject

class TaskRepository @Inject constructor(
    val taskService: TaskService,
    val taskDao: TaskDao
) /*{
    suspend fun syncData() {
        val response = taskService.getTasks()
        if (response.isSuccessful) {
            val taskList = response.body()!!
            taskList.forEach { taskDto ->
                taskDao.saveTask(Task(taskDto))
            }
        }
    }
}*/