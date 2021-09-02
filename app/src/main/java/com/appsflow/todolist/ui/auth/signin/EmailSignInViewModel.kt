package com.appsflow.todolist.ui.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsflow.todolist.di.ApplicationScope
import com.appsflow.todolist.ui.auth.signup.EmailSignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailSignInViewModel @Inject constructor(
    @ApplicationScope private val applicationScope: CoroutineScope
) : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private var currentUser = auth.currentUser

    private val signInEmailEventChannel = Channel<SignInEmailEvent>()
    val signInEmailEvent = signInEmailEventChannel.receiveAsFlow()

    fun signInEmail(email: String, password: String){
        if(currentUser == null){
            if(email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(){ task ->
                        if(task.isSuccessful){
                            currentUser = auth.currentUser
                            currentUser?.let { onUserSignedInSuccessfully(it) }
                        }
                        else{
                            val msg = "Sign In failed!\nInfo: ${task.exception}"
                            onUserSignInFailed(msg)
                        }
                    }
            }
        }
        else{
            val msg = "You are already signed in!"
            onUserSignInFailed(msg)
        }
    }

    private fun onUserSignedInSuccessfully(user: FirebaseUser) = viewModelScope.launch {
        signInEmailEventChannel.send(SignInEmailEvent.NavBackWithUser(user))
    }

    private fun onUserSignInFailed(msg: String) = viewModelScope.launch {
        signInEmailEventChannel.send(SignInEmailEvent.ThrowSignInFailedMessage(msg))
    }

    sealed class SignInEmailEvent{
        data class ThrowSignInFailedMessage(val msg: String) : EmailSignInViewModel.SignInEmailEvent()
        data class NavBackWithUser(val user: FirebaseUser) : EmailSignInViewModel.SignInEmailEvent()
    }
}