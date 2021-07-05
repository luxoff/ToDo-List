package com.appsflow.todolist.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appsflow.todolist.data.Task
import com.appsflow.todolist.databinding.TaskItemBinding

class TasksAdapter : ListAdapter<Task, TasksAdapter.TasksViewHolder>(DiffCallback()) {

    class TasksViewHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.apply {
                tvTaskTitle.text = task.title
                tvTaskTitle.paint.isStrikeThruText = task.isCompleted
                checkboxForTask.isChecked = task.isCompleted
                ivPriorityFlag.isVisible = task.isPriority
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = TaskItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<Task>() {

        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
            oldItem == newItem
    }
}