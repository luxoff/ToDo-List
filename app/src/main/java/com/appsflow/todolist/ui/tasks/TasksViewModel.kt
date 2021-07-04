package com.appsflow.todolist.ui.tasks

import androidx.lifecycle.ViewModel
import com.appsflow.todolist.data.TaskDao
//hilt fix ViewModelInject annotation

class TasksViewModel  constructor(
    private val taskDao: TaskDao
) : ViewModel() {
}