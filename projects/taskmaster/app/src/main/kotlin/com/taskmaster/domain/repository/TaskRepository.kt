package com.taskmaster.domain.repository

import com.taskmaster.domain.model.Task

interface TaskRepository {
    suspend fun create(task: Task): Task

    suspend fun getTasks(): List<Task>
}
