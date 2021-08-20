package com.appsflow.todolist.ui.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.appsflow.todolist.R
import com.appsflow.todolist.databinding.FragmentEmailSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailSignUpFragment : Fragment(R.layout.fragment_email_sign_up) {

    private val viewModel: EmailSignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentEmailSignUpBinding.bind(view)
    }

}