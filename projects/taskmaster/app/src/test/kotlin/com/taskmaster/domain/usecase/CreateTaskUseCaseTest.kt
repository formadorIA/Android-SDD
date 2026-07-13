package com.taskmaster.domain.usecase

import com.taskmaster.domain.model.Task
import com.taskmaster.domain.repository.TaskRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlinx.coroutines.test.runTest

class CreateTaskUseCaseTest {
    @Test
    fun `creates a trimmed incomplete task`() = runTest {
        val repository = FakeTaskRepository()
        val useCase = CreateTaskUseCase(
            taskRepository = repository,
            currentTimeMillis = { 1_000L },
        )

        val result = useCase("  Buy milk  ")

        assertEquals(
            CreateTaskResult.Success(
                Task(
                    id = 1,
                    title = "Buy milk",
                    isCompleted = false,
                    createdAt = 1_000L,
                ),
            ),
            result,
        )
        assertEquals(
            Task(
                id = 0,
                title = "Buy milk",
                isCompleted = false,
                createdAt = 1_000L,
            ),
            repository.createdTask,
        )
    }

    @Test
    fun `rejects a blank title after trimming`() = runTest {
        val repository = FakeTaskRepository()
        val useCase = CreateTaskUseCase(repository)

        val result = useCase("   ")

        assertEquals(CreateTaskResult.ValidationFailure, result)
        assertNull(repository.createdTask)
    }

    @Test
    fun `returns a persistence failure when creation fails`() = runTest {
        val failure = IllegalStateException("Database unavailable")
        val repository = FakeTaskRepository(createFailure = failure)
        val useCase = CreateTaskUseCase(
            taskRepository = repository,
            currentTimeMillis = { 1_000L },
        )

        val result = useCase("Buy milk")

        val persistenceFailure = assertIs<CreateTaskResult.PersistenceFailure>(result)
        assertEquals(failure, persistenceFailure.cause)
    }

    private class FakeTaskRepository(
        private val createFailure: Exception? = null,
    ) : TaskRepository {
        var createdTask: Task? = null

        override suspend fun create(task: Task): Task {
            createdTask = task
            createFailure?.let { throw it }

            return task.copy(id = 1)
        }

        override suspend fun getTasks(): List<Task> = emptyList()
    }
}
