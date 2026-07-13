package com.taskmaster.presentation.addtask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect

@Composable
fun AddTaskRoute(
    viewModel: AddTaskViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                AddTaskEvent.TaskCreated -> onNavigateBack()
            }
        }
    }

    AddTaskScreen(
        uiState = uiState,
        onTitleChanged = viewModel::onTitleChanged,
        onSubmit = viewModel::onSubmit,
        onNavigateBack = onNavigateBack,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    uiState: AddTaskUiState,
    onTitleChanged: (String) -> Unit,
    onSubmit: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Add task") },
                navigationIcon = {
                    TextButton(onClick = onNavigateBack) {
                        Text("Back")
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OutlinedTextField(
                value = uiState.title,
                onValueChange = onTitleChanged,
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isSubmitting,
                label = { Text("Task title") },
                isError = uiState.validationError != null,
                supportingText = {
                    if (uiState.validationError == AddTaskValidationError.TITLE_REQUIRED) {
                        Text("Enter a task title")
                    }
                },
                singleLine = true,
            )

            if (uiState.hasPersistenceError) {
                Text(
                    text = "Task could not be saved. Try again.",
                    color = MaterialTheme.colorScheme.error,
                )
            }

            Button(
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isSubmitting,
            ) {
                Text(
                    when {
                        uiState.isSubmitting -> "Saving…"
                        uiState.hasPersistenceError -> "Retry"
                        else -> "Add task"
                    },
                )
            }
        }
    }
}
