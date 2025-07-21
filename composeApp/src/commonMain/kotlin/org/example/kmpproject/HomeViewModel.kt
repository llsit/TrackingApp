package org.example.kmpproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.kmpproject.data.TaskRepository
import org.example.kmpproject.model.Task
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class HomeViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {
    private val _task = MutableStateFlow<TaskStateUI>(TaskStateUI.Loading)
    val task = _task.asStateFlow()

    @OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)
    fun addTask(name: String) {
        val newTask = Task(
            id = Uuid.random().toString(),
            name = name,
            isDoneToday = false,
            createdAt = Clock.System.now()
        )
        viewModelScope.launch {
            taskRepository.addTask(newTask)
        }
    }

    fun getTasks() {
        viewModelScope.launch {
            taskRepository.getTasks().collect { tasks ->
                _task.value = TaskStateUI.Success(tasks)
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
            getTasks()
        }
    }
}