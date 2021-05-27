package com.example.task_planner_app.repository.repo

import com.example.task_planner_app.repository.model.dao.UserDao
import com.example.task_planner_app.repository.remote.user.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(
    val userService: UserService,
    val userDao: UserDao
)