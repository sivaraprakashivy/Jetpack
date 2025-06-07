package com.example.jetpackcompose.stateCompose.model

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.stateCompose.datamodel.WellnessTask
import com.example.jetpackcompose.stateCompose.viewmodel.WellnessViewModel


@Composable
fun StatelessCounter(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            val size = wellnessViewModel.tasks.size + 1
            wellnessViewModel.add(WellnessTask(size, label = " Task $size", false))
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}


@Composable
fun StatefulCounter(modifier: Modifier = Modifier, wellnessViewModel: WellnessViewModel) {
    var waterCount by remember { mutableIntStateOf(0) }
    StatelessCounter(waterCount, {
        waterCount++
    }, modifier, wellnessViewModel)
}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable { mutableIntStateOf(0) }
        if (count > 0) {
            var showTask by rememberSaveable { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItem(
                    onClose = { showTask = false },
                    taskName = "Have you taken your 15 minute walk today?"
                )
            }
            Text("You've had $count glasses.")
        }

        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = { count++ }, enabled = count < 10) {
                Text("Add one")
            }
            Button(
                onClick = { count = 0 },
                Modifier.padding(start = 8.dp)
            ) {
                Text("Clear water count")
            }
        }
    }
}


