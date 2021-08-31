package com.appsflow.todolist.ui.auth.start

import androidx.lifecycle.ViewModel
import com.appsflow.todolist.di.ApplicationScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthStartViewModel @Inject constructor(
    @ApplicationScope private val applicationScope: CoroutineScope
) : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val currentUser = auth.currentUser

    fun signinWithGoogle() = applicationScope.launch {

    }

}