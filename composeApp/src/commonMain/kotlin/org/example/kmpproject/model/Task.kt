package org.example.kmpproject.model

data class Task(
    val id: Long = 0L,
    val name: String,
    val isDoneToday: Boolean = false,
    val createdAt: Long = 1
)
