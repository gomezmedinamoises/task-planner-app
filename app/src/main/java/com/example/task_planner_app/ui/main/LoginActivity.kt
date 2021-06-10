package com.example.task_planner_app.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task_planner_app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}