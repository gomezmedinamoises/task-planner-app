package com.example.task_planner_app.repository.model.dao

import androidx.room.*
import com.example.task_planner_app.repository.model.entity.User
import dagger.Provides

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun findUserById(id: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User)

    @Delete
    fun deleteUser(user: User)
}