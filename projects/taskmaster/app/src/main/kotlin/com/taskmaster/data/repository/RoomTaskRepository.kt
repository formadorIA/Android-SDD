package com.taskmaster.data.repository

import com.taskmaster.data.local.dao.TaskDao
import com.taskmaster.data.local.entity.TaskEntity
import com.taskmaster.domain.model.Task
import com.taskmaster.domain.repository.TaskRepository

class RoomTaskRepository(
    private val taskDao: TaskDao,
) : TaskRepository {
    override suspend fun create(task: Task): Task {
        val entity = task.toEntity()
        val generatedId = taskDao.insert(entity)
        return entity.copy(id = generatedId).toDomain()
    }

    override suspend fun getTasks(): List<Task> =
        taskDao.getTasks().map(TaskEntity::toDomain)

    private fun Task.toEntity(): TaskEntity =
        TaskEntity(
            id = id,
            title = title,
            isCompleted = isCompleted,
            createdAt = createdAt,
        )

    private fun TaskEntity.toDomain(): Task =
        Task(
            id = id,
            title = title,
            isCompleted = isCompleted,
            createdAt = createdAt,
        )
}
