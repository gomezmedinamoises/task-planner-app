package com.example.task_planner_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_planner_app.repository.model.entity.Task
import com.example.task_planner_app.repository.model.entity.User
import com.example.task_planner_app.repository.remote.dto.TaskDto
import com.example.task_planner_app.repository.remote.dto.UserDto
import com.example.task_planner_app.repository.repo.TaskRepository
import com.example.task_planner_app.repository.repo.UserRepository
import com.example.task_planner_app.storage.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val storage: Storage,
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    val user: User? = null
    val task: Task? = null
    val successLiveData = MutableLiveData<Boolean>()

    // User functions

    fun createUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.userService.create(UserDto("123", "Moises Gomez", "moises@mail.com", "123456789"))
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

    fun findUserById() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.userService.findUserById("60a2f73ebd44094e98397def")
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

    fun updateUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.userService.updateUser(
                "60ac4278cc818c794d63c264",
                UserDto("", "Moises David Gomez", "moisesdavid@mail.com", "1234567"))
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

    fun deleteUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.userService.deleteUser("60ac4278cc818c794d63c264")
            if (response.isSuccessful) {
                val user = response.body()!!
                Log.d("DEBUG", "User deleted: ${user.fullName}")
                successLiveData.postValue(true)
                userRepository.userDao.deleteUser(User(user))
            } else {
                response.errorBody()
            }
        }
    }

    // Task functions

    fun createTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = taskRepository.taskService.createTask(
                TaskDto(
                "123",
                "This is my first and special ask",
                "Moises Gomez",
                "Pending",
                "12/12/2021")
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

    fun findTaskById() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = taskRepository.taskService.findTaskById("6094445ad78fc32e35aaf752")
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

    fun getTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = taskRepository.taskService.getTasks()
            if(response.isSuccessful){
                val task = response.body()!!
                Log.d("DEBUG", "Get Task: $task")
                successLiveData.postValue(true)
                taskRepository.taskDao.getAllTasks()
            }else{
                response.errorBody()
                successLiveData.postValue(false)
            }
        }
    }

    fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = taskRepository.taskService.updateTask("60ac463fcc818c794d63c265",
                TaskDto("3123123","A new and difficult  task","Jeremias",
                    "12-12-2021","Incomplete")
            )
            if(response.isSuccessful){
                val task = response.body()!!
                Log.d("DEBUG", "Updated task: $task")
                successLiveData.postValue(true)
                taskRepository.taskDao.saveTask(Task(task))
            }else{
                response.errorBody()
                successLiveData.postValue(false)
            }

        }
    }

    private fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = taskRepository.taskService.deleteTask("6094445ad78fc32e35aaf752")
            if (response.isSuccessful) {
                val task = response.body()!!
                Log.d("DEBUG", "Deleted Task: $task")
                successLiveData.postValue(true)
                taskRepository.taskDao.deleteTask(Task(task))
            } else {
                response.errorBody()
                successLiveData.postValue(false)
            }
        }
    }
}