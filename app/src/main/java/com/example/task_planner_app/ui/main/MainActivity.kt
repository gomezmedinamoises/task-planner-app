package com.example.task_planner_app.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.task_planner_app.R
import com.example.task_planner_app.repository.remote.dto.TaskDto
import com.example.task_planner_app.repository.remote.dto.UserDto
import com.example.task_planner_app.repository.remote.task.TaskService
import com.example.task_planner_app.repository.remote.user.UserService
import com.example.task_planner_app.storage.Storage
import com.example.task_planner_app.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.successLiveData.observe(this, { success ->
            if (success) {
                showAllTasks()
            }

        })

        viewModel.findUserById()
    }

    private fun showAllTasks() {
        if (viewModel.task != null)
            viewModel.getTasks()
    }
}