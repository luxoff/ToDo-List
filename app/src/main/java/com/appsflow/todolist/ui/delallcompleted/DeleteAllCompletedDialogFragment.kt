package com.appsflow.todolist.ui.delallcompleted

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteAllCompletedDialogFragment : DialogFragment() {

    private val viewModel: DeleteAllCompletedViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle("Confirm action")
            .setMessage("Are you sure? This will delete all completed tasks.")
            .setNegativeButton("CANCEL", null)
            .setPositiveButton("YES") { _, _ ->
                viewModel.onConfirmClick()
            }
            .create()
}