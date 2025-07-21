package org.example.kmpproject.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.example.kmpproject.model.Task

interface TaskStorage {
    fun getTasks(): Flow<List<Task>>
    fun addTask(newTasks: Task)
    fun updateTask(task: Task)
    fun deleteTask(task: Task)
}

class TaskStorageImpl : TaskStorage {

    private val tasks = MutableStateFlow(emptyList<Task>())

    override fun getTasks(): Flow<List<Task>> = tasks

    override fun addTask(newTasks: Task) {
        tasks.value = tasks.value + newTasks
    }

    override fun updateTask(task: Task) {
        tasks.value.toMutableList().apply {
            val index = indexOfFirst { it.id == task.id }
            if (index != -1) {
                set(index, task)
            }
        }
    }

    override fun deleteTask(task: Task) {
        tasks.value = tasks.value.filter { it.id != task.id }
    }

}