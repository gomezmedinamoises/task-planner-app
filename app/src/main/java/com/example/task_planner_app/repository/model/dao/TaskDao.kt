package com.example.task_planner_app.repository.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.task_planner_app.repository.model.entity.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    fun findTaskById(id: String): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}