package org.example.kmpproject

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.kmpproject.model.Task
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    var addTask by remember { mutableStateOf(false) }
    val deleteTask = remember { mutableStateOf<Task?>(null) }
    val taskState by viewModel.task.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getTasks()
    }

    if (deleteTask.value != null) {
        ConfirmDeleteDialog(
            onConfirm = {
                viewModel.deleteTask(deleteTask.value!!)
                deleteTask.value = null
            },
            onDismiss = { deleteTask.value = null }
        )
    }

    if (addTask) {
        TaskEditDialog(
            "",
            {
                viewModel.addTask(it)
                addTask = false
            },
            { addTask = false }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task/Habit Tracker") },
                actions = {
                    IconButton(onClick = { addTask = true }) {
                        Text("Add Task")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            when (taskState) {
                is TaskStateUI.Error -> {
                    TaskError()
                }

                TaskStateUI.Loading -> {
                    AnimatedVisibility(true) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                is TaskStateUI.Success -> {
                    AnimatedVisibility(true) {
                        val tasks = (taskState as TaskStateUI.Success).tasks
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(4.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Tasks done today: ${tasks.count { it.isDoneToday }}")
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            if (tasks.isEmpty()) {
                                TaskError()
                            } else {
                                LazyColumn {
                                    items(tasks) { task ->
                                        TaskItem(
                                            task = task,
                                            onCheckedChange = { task, checked -> },
                                            onEdit = { task -> },
                                            onDelete = { task -> deleteTask.value = task })
                                        HorizontalDivider()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TaskError() {
    AnimatedVisibility(true) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("No tasks yet. Tap + to add.")
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onCheckedChange: (Task, Boolean) -> Unit,
    onEdit: (Task) -> Unit,
    onDelete: (Task) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Checkbox(
            checked = task.isDoneToday,
            onCheckedChange = { checked -> onCheckedChange(task, checked) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(task.name)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { onEdit(task) }) {
            Text("Edit")
        }
        IconButton(onClick = { onDelete(task) }) {
            Text("Delete")
        }
    }
}
