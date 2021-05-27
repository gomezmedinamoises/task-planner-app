package com.example.task_planner_app.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.task_planner_app.R
import com.example.task_planner_app.repository.remote.auth.AuthService
import com.example.task_planner_app.repository.remote.dto.LoginDto
import com.example.task_planner_app.storage.Storage
import com.example.task_planner_app.viewmodel.LoginActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.successLiveData.observe(this, { success ->
            if (success) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        })

        viewModel.auth()

    }


}