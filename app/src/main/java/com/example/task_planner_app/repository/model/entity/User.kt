package com.example.task_planner_app.repository.model.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.task_planner_app.repository.remote.dto.UserDto

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val id: String,
    val fullName: String,
    val email: String,
    val passwordHash: String
) {
    constructor(userDto: UserDto): this(
        0,
        userDto.id,
        userDto.fullName,
        userDto.email,
        userDto.passwordHash
    )
}