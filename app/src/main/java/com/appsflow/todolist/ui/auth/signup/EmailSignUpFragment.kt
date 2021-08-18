package com.appsflow.todolist.ui.auth.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.appsflow.todolist.R
import com.appsflow.todolist.databinding.EmailSignUpFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailSignUpFragment : Fragment(R.layout.email_sign_up_fragment) {

    private val viewModel: EmailSignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = EmailSignUpFragmentBinding.bind(view)
    }

}