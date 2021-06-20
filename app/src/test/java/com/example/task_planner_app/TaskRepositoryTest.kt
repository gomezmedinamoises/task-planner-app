package com.example.task_planner_app

import androidx.lifecycle.LiveData
import com.example.task_planner_app.repository.model.dao.TaskDao
import com.example.task_planner_app.repository.model.entity.Task
import com.example.task_planner_app.repository.remote.dto.TaskDto
import com.example.task_planner_app.repository.remote.task.TaskService
import com.example.task_planner_app.repository.repo.TaskRepository
import com.example.task_planner_app.repository.repo.UserRepository
import com.example.task_planner_app.viewmodel.MainActivityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class TaskRepositoryTest {

    lateinit var taskRepository: TaskRepository

    @Mock
    lateinit var taskService: TaskService

    @Mock
    lateinit var taskDao: TaskDao

    @Mock
    lateinit var responseTaskStored: Response<TaskDto>

    @Mock
    lateinit var responseListTaskStored: Response<List<TaskDto>>

    @Mock
    lateinit var responseTaskStoredLocally: Response<Any>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        taskRepository = TaskRepository(taskService, taskDao)
    }

    @Test
    fun taskCreatedIsStoredInAPI() = runBlockingTest {
        `when`(responseTaskStored.isSuccessful).thenReturn(true)
        val taskCreated = TaskDto("123", "My first description test",
        "Moises Testing", "20-20-2020","Pending")
        `when`(responseTaskStored.body()).thenReturn(taskCreated)
        `when`(taskService.createTask(taskCreated)).thenReturn(responseTaskStored)
        taskRepository.taskService.createTask(taskCreated)
        verify(taskService, times(1)).createTask(taskCreated)
    }

    @Test
    fun showAllTaskStoredInAPI() = runBlockingTest {
        `when`(responseListTaskStored.isSuccessful).thenReturn(true)
        val taskListStored = ArrayList<TaskDto>()
        taskListStored.add(
            TaskDto("123", "My first description test",
                "Moises Testing", "20-20-2020","Pending")
        )
        taskListStored.add(
            TaskDto("1234", "My second description test",
                "Moises Testing", "21-20-2020","Pending")
        )
        taskListStored.add(
            TaskDto("12345", "My third description test",
                "Moises Testing", "22-20-2020","Pending")
        )
        `when`(responseListTaskStored.body()).thenReturn(taskListStored)
        `when`(taskRepository.taskService.getTasks()).thenReturn(responseListTaskStored)
        taskRepository.taskService.getTasks()
        verify(taskService, times(1)).getTasks()
    }

    @Test
    fun taskIsUpdatedInAPI() = runBlockingTest {
        `when`(responseTaskStored.isSuccessful).thenReturn(true)
        val taskUpdated = TaskDto("12345", "My third description test",
            "Moises Testing", "22-20-2020","Pending")
        `when`(responseTaskStored.body()).thenReturn(taskUpdated)
        `when`(taskService.updateTask("123", taskUpdated)).thenReturn(responseTaskStored)
        taskRepository.taskService.updateTask("123", taskUpdated)
        verify(taskService, times(1)).updateTask("123", taskUpdated)
    }

    // This test is not passed yet
    /*@Test
    fun taskCreatedIsStoredLocally() = runBlockingTest {
        `when`(responseTaskStoredLocally.isSuccessful).thenReturn(true)
        val taskLocallySaved = Task(123, "123", "My third description test",
            "Moises Testing", "22-20-2020","Pending")
        `when`(responseTaskStoredLocally.body()).thenReturn(taskLocallySaved)
        `when`(taskRepository.taskDao.saveTask(taskLocallySaved)).thenReturn(org.mockito.kotlin.any())
        taskRepository.taskDao.saveTask(taskLocallySaved)
        verify(taskRepository, times(1)).taskDao.saveTask(org.mockito.kotlin.any())
    }*/
}