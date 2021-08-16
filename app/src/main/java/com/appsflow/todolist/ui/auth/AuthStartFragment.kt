package com.appsflow.todolist.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.appsflow.todolist.R
import com.appsflow.todolist.databinding.FragmentAuthStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthStartFragment : Fragment(R.layout.fragment_auth_start) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAuthStartBinding.bind(view)
    }
}