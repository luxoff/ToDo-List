package com.appsflow.todolist.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.appsflow.todolist.data.PreferencesManager
import com.appsflow.todolist.data.SortOrder
import com.appsflow.todolist.data.Task
import com.appsflow.todolist.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    val searchQuery = MutableStateFlow("")
    val prefFlow = preferencesManager.preferencesFlow

    @ExperimentalCoroutinesApi
    private val taskFlow = combine(
        searchQuery,
        prefFlow
    ) { query, filterPrefs ->
        Pair(query, filterPrefs)
    }.flatMapLatest { (query, filterPrefs) ->
        taskDao.getTasks(query, filterPrefs.sortOrder, filterPrefs.hideCompleted)
    }

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompleted(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onTaskClicked(task: Task) {

    }

    fun onTaskCheckboxChecked(task: Task, isChecked: Boolean) = viewModelScope.launch {
        taskDao.updateTask(task.copy(isCompleted = isChecked))
    }

    @ExperimentalCoroutinesApi
    val tasks = taskFlow.asLiveData()
}