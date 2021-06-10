package com.example.task_planner_app.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task_planner_app.repository.model.dao.TaskDao
import com.example.task_planner_app.repository.model.dao.UserDao
import com.example.task_planner_app.repository.model.entity.Task
import com.example.task_planner_app.repository.model.entity.User

@Database(entities = [User::class, Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
}