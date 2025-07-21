package org.example.kmpproject.data

import kotlinx.coroutines.flow.Flow
import org.example.kmpproject.model.Task

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>
    fun addTask(newTasks: Task)
    fun updateTask(task: Task)
    fun deleteTask(task: Task)
}

class TaskRepositoryImpl(private val taskStorage: TaskStorage) : TaskRepository {
    override fun getTasks(): Flow<List<Task>> {
        return taskStorage.getTasks()
    }

    override fun addTask(newTasks: Task) {
        taskStorage.addTask(newTasks)
    }

    override fun updateTask(task: Task) {
        taskStorage.updateTask(task)
    }

    override fun deleteTask(task: Task) {
        taskStorage.deleteTask(task)
    }
}