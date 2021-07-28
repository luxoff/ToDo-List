package com.appsflow.todolist.ui.edittask

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.appsflow.todolist.R
import com.appsflow.todolist.databinding.FragmentEditTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditTaskFragment : Fragment(R.layout.fragment_edit_task) {
    private val viewModel: EditTaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEditTaskBinding.bind(view)

        binding.apply {
            etTitleName.setText(viewModel.taskTitle)
            checkboxIsPriority.isChecked = viewModel.taskPriority
            checkboxIsPriority.jumpDrawablesToCurrentState()
        }
    }
}