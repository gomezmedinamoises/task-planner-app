package com.example.task_planner_app.storage

interface Storage {

    fun saveToken(token: String)

    fun getToken(): String

    fun clear()
}