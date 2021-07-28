package com.appsflow.todolist.ui.edittask

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.appsflow.todolist.data.Task
import com.appsflow.todolist.data.TaskDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val taskDao: TaskDao,
    private val state: SavedStateHandle
) : ViewModel() {

    val task = state.get<Task>("task")

    var taskTitle: String = (state.get<String>("taskTitle") ?: task?.title) ?: ""
        set(value) {
            field = value
            state.set("taskTitle", value)
        }

    var taskPriority: Boolean = (state.get<Boolean>("taskPriority") ?: task?.isPriority) ?: false
        set(value) {
            field = value
            state.set("taskPriority", value)
        }


}