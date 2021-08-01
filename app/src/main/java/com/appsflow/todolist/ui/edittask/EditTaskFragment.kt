package com.appsflow.todolist.ui.edittask

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.appsflow.todolist.R
import com.appsflow.todolist.databinding.FragmentEditTaskBinding
import com.appsflow.todolist.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

            etTitleName.addTextChangedListener {
                viewModel.taskTitle = it.toString()
            }

            checkboxIsPriority.setOnCheckedChangeListener { _, isChecked ->
                viewModel.taskPriority = isChecked
            }

            fabSaveTask.setOnClickListener {
                viewModel.onFabSaveClick()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addEditTaskEvent.collect { event ->
                when (event) {
                    is EditTaskViewModel.AddEditTaskEvent.NavBackWithResult -> {
                        binding.etTitleName.clearFocus()
                        setFragmentResult(
                            "add_edit_task_request",
                            bundleOf("add_edit_task_result" to event.result)
                        )
                        findNavController().popBackStack()
                    }
                    is EditTaskViewModel.AddEditTaskEvent.ThrowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }
                }.exhaustive
            }
        }
    }
}