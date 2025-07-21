package org.example.kmpproject.model

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class Task(
    val id: String,
    val name: String,
    val isDoneToday: Boolean = false,
    val createdAt: Instant
)
