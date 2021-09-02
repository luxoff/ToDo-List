package com.appsflow.todolist.ui.auth.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import com.appsflow.todolist.R
import com.appsflow.todolist.databinding.FragmentEmailSignInBinding
import com.appsflow.todolist.ui.auth.signup.EmailSignUpFragmentDirections
import com.appsflow.todolist.utils.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class EmailSignInFragment : Fragment(R.layout.fragment_email_sign_in) {

    private val viewModel: EmailSignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentEmailSignInBinding.bind(view)

        binding.apply {
            btnSignIn.setOnClickListener {
                viewModel.signInEmail(etEmailInput.text.toString().trim(),
                    etPasswordInput.text.toString().trim())
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.signInEmailEvent.collect { event ->
                when(event){
                    is EmailSignInViewModel.SignInEmailEvent.NavBackWithUser -> {
                        Toast.makeText(requireContext(), "Successful sign in!", Toast.LENGTH_SHORT).show()
                        val action = EmailSignInFragmentDirections.actionEmailSignInFragmentToTasksFragment2()
                        findNavController().navigate(action)
                    }
                    is EmailSignInViewModel.SignInEmailEvent.ThrowSignInFailedMessage -> {
                        Toast.makeText(requireContext(), event.msg, Toast.LENGTH_LONG).show()
                    }
                }.exhaustive
            }
        }
    }

}