package com.taskmaster.presentation.addtask

import com.taskmaster.domain.model.Task
import com.taskmaster.domain.repository.TaskRepository
import com.taskmaster.domain.usecase.CreateTaskUseCase
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class AddTaskViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `shows title validation feedback without persisting a blank title`() = runTest {
        val repository = FakeTaskRepository()
        val viewModel = AddTaskViewModel(CreateTaskUseCase(repository))

        viewModel.onTitleChanged("   ")
        viewModel.onSubmit()
        advanceUntilIdle()

        assertEquals(AddTaskValidationError.TITLE_REQUIRED, viewModel.uiState.value.validationError)
        assertFalse(viewModel.uiState.value.isSubmitting)
        assertNull(repository.createdTask)
    }

    @Test
    fun `shows persistence feedback when task creation fails`() = runTest {
        val repository = FakeTaskRepository(createFailure = IllegalStateException("Database unavailable"))
        val viewModel = AddTaskViewModel(CreateTaskUseCase(repository))

        viewModel.onTitleChanged("Buy milk")
        viewModel.onSubmit()
        advanceUntilIdle()

        assertEquals("Buy milk", viewModel.uiState.value.title)
        assertTrue(viewModel.uiState.value.hasPersistenceError)
        assertFalse(viewModel.uiState.value.isSubmitting)
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
