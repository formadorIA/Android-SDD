package com.taskmaster.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.taskmaster.domain.usecase.CreateTaskUseCase
import com.taskmaster.presentation.addtask.AddTaskRoute
import com.taskmaster.presentation.addtask.AddTaskViewModel

object AddTaskDestination {
    const val route = "add_task"
}

fun NavController.navigateToAddTask() {
    navigate(AddTaskDestination.route)
}

fun NavGraphBuilder.addTaskDestination(
    navController: NavHostController,
    createTaskUseCase: CreateTaskUseCase,
) {
    composable(route = AddTaskDestination.route) {
        val viewModel: AddTaskViewModel = viewModel(
            factory = AddTaskViewModel.factory(createTaskUseCase),
        )

        AddTaskRoute(
            viewModel = viewModel,
            onNavigateBack = { navController.popBackStack() },
        )
    }
}
