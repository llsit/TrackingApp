package org.example.kmpproject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.kmpproject.model.Task

@Composable
fun StatsScreen(tasks: List<Task>, streak: Int) {
    Column(Modifier.padding(16.dp)) {
        Text("Statistics", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("Tasks completed: ${tasks.count { it.isDoneToday }}")
        Text("Current streak: $streak")
        // เพิ่มกราฟ/summary ได้ตามฟีเจอร์เลย
    }
}
