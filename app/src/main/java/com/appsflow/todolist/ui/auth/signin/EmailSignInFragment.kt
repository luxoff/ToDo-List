package com.appsflow.todolist.ui.auth.signin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.appsflow.todolist.R
import com.appsflow.todolist.databinding.EmailSignInFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailSignInFragment : Fragment(R.layout.email_sign_in_fragment) {

    private val viewModel: EmailSignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = EmailSignInFragmentBinding.bind(view)
    }

}