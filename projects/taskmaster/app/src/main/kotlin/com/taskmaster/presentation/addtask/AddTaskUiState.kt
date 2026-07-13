package com.taskmaster.presentation.addtask

data class AddTaskUiState(
    val title: String = "",
    val validationError: AddTaskValidationError? = null,
    val isSubmitting: Boolean = false,
    val hasPersistenceError: Boolean = false,
)

enum class AddTaskValidationError {
    TITLE_REQUIRED,
}
