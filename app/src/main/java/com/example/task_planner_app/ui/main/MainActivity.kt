package com.example.task_planner_app.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.task_planner_app.R
import com.example.task_planner_app.analytics.AnalyticsReporter
import com.example.task_planner_app.repository.RetrofitGenerator
import com.example.task_planner_app.repository.auth.AuthService
import com.example.task_planner_app.repository.dto.LoginDto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var authService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth()

    }

    private fun auth() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = authService.auth(LoginDto("jeremias@mail.com", "password"))
            if (response.isSuccessful) {
                val tokenDto = response.body()
                Log.d("DEBUG", "tokenDto: $tokenDto")
                startActivity(Intent(application, SecondActivity::class.java))
            } else {
                response.errorBody()
            }
        }
    }
}