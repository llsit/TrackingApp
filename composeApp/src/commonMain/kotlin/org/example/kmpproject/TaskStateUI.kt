package org.example.kmpproject

import org.example.kmpproject.model.Task

sealed class TaskStateUI {
    object Loading : TaskStateUI()
    data class Success(val tasks: List<Task>) : TaskStateUI()
    data class Error(val message: String) : TaskStateUI()
}