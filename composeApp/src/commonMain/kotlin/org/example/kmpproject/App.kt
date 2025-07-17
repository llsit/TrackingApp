package org.example.kmpproject

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.example.kmpproject.model.Task
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        AnimatedVisibility(true) {
            HomeScreen(
                tasks = listOf(
                    Task(
                        id = 1,
                        name = "task 1",
                        isDoneToday = true,
                        createdAt = 2
                    ),
                    Task(
                        id = 2,
                        name = "task 2",
                        isDoneToday = true,
                        createdAt = 3
                    ),Task(
                        id = 3,
                        name = "task 1",
                        isDoneToday = false,
                        createdAt = 4
                    ),
                    Task(
                        id = 4,
                        name = "task 4",
                        isDoneToday = false,
                        createdAt = 5
                    )
                ),
                {},
                { _, _ -> }
            )
        }
    }
}