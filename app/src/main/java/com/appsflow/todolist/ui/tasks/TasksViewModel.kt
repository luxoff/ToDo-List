package com.appsflow.todolist.ui.tasks

import androidx.lifecycle.ViewModel
import com.appsflow.todolist.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
) : ViewModel() {
}