package com.example.task_planner_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_planner_app.repository.remote.auth.AuthService
import com.example.task_planner_app.repository.remote.dto.LoginDto
import com.example.task_planner_app.storage.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(
    private val authService: AuthService,
    private val storage: Storage
) : ViewModel() {

    val successLiveData = MutableLiveData<Boolean>()

    fun auth() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authService.auth(LoginDto("jeremias@mail.com", "password"))
            if (response.isSuccessful) {
                val tokenDto = response.body()!!
                Log.d("DEBUG", "tokenDto: $tokenDto")
                storage.saveToken(tokenDto.token)
                successLiveData.postValue(true)
            } else {
                response.errorBody()
                successLiveData.postValue(false)
            }
        }
    }
}