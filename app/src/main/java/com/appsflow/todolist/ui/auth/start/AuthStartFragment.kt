package com.appsflow.todolist.ui.auth.start

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.appsflow.todolist.R
import com.appsflow.todolist.databinding.FragmentAuthStartBinding
import com.appsflow.todolist.ui.tasks.TasksFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthStartFragment : Fragment(R.layout.fragment_auth_start) {

    private val viewModel: AuthStartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAuthStartBinding.bind(view)

        binding.apply {
            btnSignInWithGoogle.setOnClickListener { _ ->
                viewModel.signinWithGoogle()
            }

            btnSignInWithEmail.setOnClickListener { _ ->
                AlertDialog.Builder(requireContext())
                    .setTitle("Already have an account?")
                    .setCancelable(true)
                    .setNegativeButton("No, sign up") { _, _ ->
                        val action = AuthStartFragmentDirections.actionAuthStartFragmentToEmailSignUpFragment(
                            "Sign Up with Email"
                        )
                        findNavController().navigate(action)
                    }
                    .setPositiveButton("Yes, log in") { _, _ ->
                        val action = AuthStartFragmentDirections.actionAuthStartFragmentToEmailSignInFragment(
                            "Sign In with Email"
                        )
                        findNavController().navigate(action)
                    }
                    .create().show()
            }
        }
    }

}