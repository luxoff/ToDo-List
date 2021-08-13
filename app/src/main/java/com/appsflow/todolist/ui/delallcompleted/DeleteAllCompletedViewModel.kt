package com.appsflow.todolist.ui.delallcompleted

import androidx.lifecycle.ViewModel
import com.appsflow.todolist.data.TaskDao
import com.appsflow.todolist.di.ApplicationScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteAllCompletedViewModel @Inject constructor(
    private val taskDao: TaskDao,
    @ApplicationScope private val applicationScope: CoroutineScope
) : ViewModel() {

    fun onConfirmClick() = applicationScope.launch {
        taskDao.deleteAllCompletedTasks()
    }
}