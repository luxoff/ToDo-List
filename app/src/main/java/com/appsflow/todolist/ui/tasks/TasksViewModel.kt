package com.appsflow.todolist.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.appsflow.todolist.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
) : ViewModel() {

    val searchQuery = MutableStateFlow("")
    @ExperimentalCoroutinesApi
    private val taskFlow = searchQuery.flatMapLatest {
        taskDao.getTasks(it)
    }

    @ExperimentalCoroutinesApi
    val tasks = taskFlow.asLiveData()
}