package com.taskmaster.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.taskmaster.data.local.entity.TaskEntity

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: TaskEntity): Long

    @Query(
        """
        SELECT * FROM tasks
        ORDER BY isCompleted ASC, createdAt DESC
        """,
    )
    suspend fun getTasks(): List<TaskEntity>
}
