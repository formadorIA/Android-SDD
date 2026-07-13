package com.taskmaster.presentation.addtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.taskmaster.domain.usecase.CreateTaskResult
import com.taskmaster.domain.usecase.CreateTaskUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val createTaskUseCase: CreateTaskUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddTaskUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<AddTaskEvent>()
    val events = _events.asSharedFlow()

    fun onTitleChanged(title: String) {
        if (_uiState.value.isSubmitting) {
            return
        }

        _uiState.update {
            it.copy(
                title = title,
                validationError = null,
                hasPersistenceError = false,
            )
        }
    }

    fun onSubmit() {
        if (_uiState.value.isSubmitting) {
            return
        }

        val title = _uiState.value.title

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    validationError = null,
                    hasPersistenceError = false,
                    isSubmitting = true,
                )
            }

            when (createTaskUseCase(title)) {
                is CreateTaskResult.Success -> {
                    _uiState.update { it.copy(isSubmitting = false) }
                    _events.emit(AddTaskEvent.TaskCreated)
                }

                CreateTaskResult.ValidationFailure -> {
                    _uiState.update {
                        it.copy(
                            validationError = AddTaskValidationError.TITLE_REQUIRED,
                            isSubmitting = false,
                        )
                    }
                }

                is CreateTaskResult.PersistenceFailure -> {
                    _uiState.update {
                        it.copy(
                            hasPersistenceError = true,
                            isSubmitting = false,
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun factory(createTaskUseCase: CreateTaskUseCase): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(AddTaskViewModel::class.java)) {
                        return AddTaskViewModel(createTaskUseCase) as T
                    }

                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
    }
}

sealed class AddTaskEvent {
    object TaskCreated : AddTaskEvent()
}
