package com.example.task_planner_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_planner_app.repository.model.entity.Task
import com.example.task_planner_app.repository.model.entity.User
import com.example.task_planner_app.repository.remote.dto.TaskDto
import com.example.task_planner_app.repository.remote.dto.UserDto
import com.example.task_planner_app.repository.repo.TaskRepository
import com.example.task_planner_app.repository.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    // val user: User? = null
    //val task: Task? = null

    private val _taskList: MutableLiveData<List<TaskDto>> = MutableLiveData<List<TaskDto>>()
    val taskList : LiveData<List<TaskDto>> get() = _taskList

    val successLiveData = MutableLiveData<Boolean>()

    // First value = Delete was successful or not
    // Second value = Contains the position in the home view recycler view to be deleted when it is successful
    val successDeleteLiveData = MutableLiveData<Pair<Boolean, Int>>()

    // User functions

    fun createUser(id: String, fullName: String, email: String, passwordHash: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                userRepository.userService.createUser(UserDto(id, fullName, email, passwordHash))
            if (response.isSuccessful) {
                val user = response.body()!!
                Log.d("DEBUG", "Create new user: ${user.fullName}")
                successLiveData.postValue(true)
                userRepository.userDao.saveUser(User(user))
            } else {
                response.errorBody()
                successLiveData.postValue(false)
            }
        }
    }

    fun findUserById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.userService.findUserById(id)
            if (response.isSuccessful) {
                val user = response.body()!!
                Log.d("Developer", "userDto: ${user.fullName}")
                successLiveData.postValue(true)
                userRepository.userDao.findUserById(user.id)
            } else {
                response.errorBody()
                successLiveData.postValue(false)
            }
        }
    }

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.userService.getUsers()
            if (response.isSuccessful) {
                val user = response.body()!!
                Log.d("DEBUG", "Get userId: $user")
                successLiveData.postValue(true)
                userRepository.userDao.getAllUsers()
            } else {
                response.errorBody()
                successLiveData.postValue(false)
            }
        }
    }

    fun updateUser(id: String, userDto: UserDto) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.userService.updateUser(
                id, userDto
            )
            if (response.isSuccessful) {
                val user = response.body()!!
                Log.d("DEBUG", "User updated: ${user.fullName}")
                successLiveData.postValue(true)
                userRepository.userDao.saveUser(User(user))
            } else {
                response.errorBody()
                successLiveData.postValue(false)
            }
        }
    }

    fun deleteTask(id: String, itemViewPosition : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = taskRepository.taskService.deleteTask(id)
                Log.d("DEBUG", "Task deleted: $response")
                successDeleteLiveData.postValue(Pair(true, itemViewPosition))
        }
    }

    // Task functions

    fun createTask(description: String, responsible: String, date: String, status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = taskRepository.taskService.createTask(
                TaskDto(
                    "123", description, responsible, date, status
                )
            )
            if (response.isSuccessful) {
                val task = response.body()!!
                Log.d("DEBUG", "Create new task: $task")
                successLiveData.postValue(true)
                taskRepository.taskDao.saveTask(Task(task))
            } else {
                response.errorBody()
                successLiveData.postValue(false)
            }
        }
    }

    fun findTaskById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = taskRepository.taskService.findTaskById(id)
            if (response.isSuccessful) {
                val task = response.body()!!
                Log.d("DEBUG", "Find task by id: ${task.id}")
                successLiveData.postValue(true)
                taskRepository.taskDao.findTaskById(task.id)
            } else {
                response.errorBody()
                successLiveData.postValue(false)
            }
        }
    }

    /*fun getTaskList(taskAdapter: TaskAdapter) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = taskRepository.taskService.getTasks()
            if (response.isSuccessful) {
                taskAdapter.updateTaskList(response.body()!!)
            }
        }
    }*/

    

    fun getTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = taskRepository.taskService.getTasks()

            if (response.isSuccessful) {

                val tasksReceived : List<TaskDto> = response.body() ?: ArrayList<TaskDto>()

                _taskList.postValue(tasksReceived)

            } else {
                response.errorBody()
                successLiveData.postValue(false)
            }
        }
    }

    fun updateTask(id: String, taskDto: TaskDto) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = taskRepository.taskService.updateTask(id, taskDto)
            if (response.isSuccessful) {
                val task = response.body()!!
                Log.d("DEBUG", "Updated task: $task")
                successLiveData.postValue(true)
                // taskRepository.taskDao.saveTask(Task(task))
            } else {
                response.errorBody()
                successLiveData.postValue(false)
            }

        }
    }

}