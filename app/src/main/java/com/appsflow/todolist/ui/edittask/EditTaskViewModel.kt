package com.appsflow.todolist.ui.edittask

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsflow.todolist.data.Task
import com.appsflow.todolist.data.TaskDao
import com.appsflow.todolist.ui.main.ADD_TASK_RESULT_OK
import com.appsflow.todolist.ui.main.EDIT_TASK_RESULT_OK
import com.google.android.material.snackbar.Snackbar
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val taskDao: TaskDao,
    private val state: SavedStateHandle
) : ViewModel() {

    private val addEditTaskEventChannel = Channel<AddEditTaskEvent>()
    val addEditTaskEvent = addEditTaskEventChannel.receiveAsFlow()

    fun onFabSaveClick() {
        if (taskTitle.isBlank()) {
            throwInvalidInputMessage("Task title cannot be empty.")
            return
        }

        if (task != null) {
            val updatedTask = task.copy(title = taskTitle, isPriority = taskPriority, created = System.currentTimeMillis())
            updateTask(updatedTask)
        } else {
            val newTask = Task(title = taskTitle, isPriority = taskPriority, created = System.currentTimeMillis())
            createTask(newTask)
        }
    }

    private fun throwInvalidInputMessage(msg: String) = viewModelScope.launch {
        addEditTaskEventChannel.send(AddEditTaskEvent.ThrowInvalidInputMessage(msg))
    }

    private fun createTask(newTask: Task) = viewModelScope.launch {
        taskDao.insert(newTask)
        addEditTaskEventChannel.send(AddEditTaskEvent.NavBackWithResult(ADD_TASK_RESULT_OK))
    }

    private fun updateTask(updatedTask: Task) = viewModelScope.launch {
        taskDao.updateTask(updatedTask)
        addEditTaskEventChannel.send(AddEditTaskEvent.NavBackWithResult(EDIT_TASK_RESULT_OK))
    }

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

    val lastChangedDateFormatted: String =
        (state.get<String>("dateFormatted") ?: task?.createdDateFormatted ?: "")

    sealed class AddEditTaskEvent {
        data class ThrowInvalidInputMessage(val msg: String) : AddEditTaskEvent()
        data class NavBackWithResult(val result: Int) : AddEditTaskEvent()
    }
}