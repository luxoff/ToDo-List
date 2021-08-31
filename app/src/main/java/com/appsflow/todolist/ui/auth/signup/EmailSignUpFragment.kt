package com.appsflow.todolist.ui.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.appsflow.todolist.R
import com.appsflow.todolist.databinding.FragmentEmailSignUpBinding
import com.appsflow.todolist.ui.auth.start.AuthStartFragmentDirections
import com.appsflow.todolist.utils.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class EmailSignUpFragment : Fragment(R.layout.fragment_email_sign_up) {

    private val viewModel: EmailSignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentEmailSignUpBinding.bind(view)
        binding.apply {
            btnSignUp.setOnClickListener { _ ->
                viewModel.signupWithEmailAndPassword(etEmailInput.text.toString().trim(),
                    etPasswordInput.text.toString().trim(),
                    etPasswordRepeatInput.text.toString().trim())
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.signUpEmailEvent.collect { event ->
                when(event){
                    is EmailSignUpViewModel.SignUpEmailEvent.ThrowSignUpFailedMessage -> {
                        Toast.makeText(requireContext(), event.msg, Toast.LENGTH_LONG).show()
                    }
                    is EmailSignUpViewModel.SignUpEmailEvent.NavBackWithUser -> {
                        Toast.makeText(requireContext(), "Successful sign up!", Toast.LENGTH_SHORT).show()
                        val action = EmailSignUpFragmentDirections.actionEmailSignUpFragmentToTasksFragment2()
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }
    }

}