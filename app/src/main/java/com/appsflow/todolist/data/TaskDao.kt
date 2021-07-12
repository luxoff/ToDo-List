package com.appsflow.todolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table WHERE title LIKE '%' || :searchQuery || '%' ORDER BY isPriority DESC")
    fun getTasks(searchQuery: String): Flow<List<Task>>

    @Query("SELECT * FROM task_table ORDER BY title DESC")
    fun sortTasksByName(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun delete(task: Task)

}