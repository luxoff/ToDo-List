package com.appsflow.todolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table WHERE (isCompleted != :hideCompletedTasks or isCompleted = 0) AND title LIKE '%' || :searchQuery || '%' ORDER BY isPriority DESC, title")
    fun getTasksSortedByName(searchQuery: String, hideCompletedTasks: Boolean): Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE (isCompleted != :hideCompletedTasks or isCompleted = 0) AND title LIKE '%' || :searchQuery || '%' ORDER BY isPriority DESC, created")
    fun getTasksSortedByDate(searchQuery: String, hideCompletedTasks: Boolean): Flow<List<Task>>

    fun getTasks(
        query: String,
        sortOreder: SortOrder,
        hideCompletedTasks: Boolean
    ): Flow<List<Task>> = when (sortOreder) {
        SortOrder.BY_NAME -> getTasksSortedByName(query, hideCompletedTasks)
        SortOrder.BY_DATE -> getTasksSortedByDate(query, hideCompletedTasks)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun delete(task: Task)

}