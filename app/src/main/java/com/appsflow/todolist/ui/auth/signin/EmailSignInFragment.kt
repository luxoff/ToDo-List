package com.appsflow.todolist.ui.auth.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.appsflow.todolist.R
import com.appsflow.todolist.databinding.FragmentEmailSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailSignInFragment : Fragment(R.layout.fragment_email_sign_in) {

    private val viewModel: EmailSignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentEmailSignInBinding.bind(view)
    }

}