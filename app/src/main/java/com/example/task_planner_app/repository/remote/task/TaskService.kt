package com.example.task_planner_app.repository.remote.task

import com.example.task_planner_app.repository.remote.dto.TaskDto
import retrofit2.Response
import retrofit2.http.*

interface TaskService {

    @POST("tasks")
    suspend fun createTask(@Body taskDto: TaskDto): Response<TaskDto>

    @GET("tasks")
    suspend fun getTasks(): Response<List<TaskDto>>

    @GET("tasks/{id}")
    suspend fun findTaskById(@Path("id") id:String): Response<TaskDto>

    @PUT("tasks/{id}")
    suspend fun updateTask(@Path("id")id: String, @Body taskDto: TaskDto):Response<TaskDto>

    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id:String): Response<TaskDto>
}