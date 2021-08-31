package com.appsflow.todolist.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsflow.todolist.ui.edittask.EditTaskViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailSignUpViewModel @Inject constructor(

) : ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth
    private var currentUser = auth.currentUser

    private val signUpEmailEventChannel = Channel<EmailSignUpViewModel.SignUpEmailEvent>()
    val signUpEmailEvent = signUpEmailEventChannel.receiveAsFlow()

    fun signupWithEmailAndPassword(email: String, password: String, repeatPassword: String){
        if(currentUser == null){
            if(email.isNotEmpty() && password.isNotEmpty()){
                if(password == repeatPassword) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener() { task ->
                            if(task.isSuccessful){
                                currentUser = auth.currentUser
                                currentUser?.let { onSignUpSuccessful(it) }
                            }
                            else{
                                val msg: String = "Sign Up failed!\n${task.exception}"
                                onSignUpFailed(msg)
                            }
                        }
                } else{ onSignUpFailed("Passwords don't match!") }
            } else {onSignUpFailed("Email and password shouldn't be empty")}
        }
    }

    private fun onSignUpSuccessful(user: FirebaseUser) = viewModelScope.launch {
        signUpEmailEventChannel.send(SignUpEmailEvent.NavBackWithUser(user))
    }

    private fun onSignUpFailed(msg: String) = viewModelScope.launch {
        signUpEmailEventChannel.send(SignUpEmailEvent.ThrowSignUpFailedMessage(msg))
    }

    sealed class SignUpEmailEvent {
        data class ThrowSignUpFailedMessage(val msg: String) : SignUpEmailEvent()
        data class NavBackWithUser(val user: FirebaseUser) : SignUpEmailEvent()
    }
}