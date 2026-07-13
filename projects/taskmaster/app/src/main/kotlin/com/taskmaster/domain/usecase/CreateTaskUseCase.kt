package com.taskmaster.domain.usecase

import com.taskmaster.domain.model.Task
import com.taskmaster.domain.repository.TaskRepository

class CreateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val currentTimeMillis: () -> Long = { System.currentTimeMillis() },
) {
    suspend operator fun invoke(title: String): CreateTaskResult {
        val normalizedTitle = title.trim()

        if (normalizedTitle.isBlank()) {
            return CreateTaskResult.ValidationFailure
        }

        val task = Task(
            id = 0,
            title = normalizedTitle,
            isCompleted = false,
            createdAt = currentTimeMillis(),
        )

        return try {
            CreateTaskResult.Success(taskRepository.create(task))
        } catch (exception: Exception) {
            CreateTaskResult.PersistenceFailure(exception)
        }
    }
}

sealed class CreateTaskResult {
    data class Success(
        val task: Task,
    ) : CreateTaskResult()

    object ValidationFailure : CreateTaskResult()

    data class PersistenceFailure(
        val cause: Exception,
    ) : CreateTaskResult()
}
