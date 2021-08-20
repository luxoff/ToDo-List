package com.appsflow.todolist.ui.auth.signup

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmailSignUpViewModel @Inject constructor(

) : ViewModel() {
    /*private var auth: FirebaseAuth = Firebase.auth
    private val currentUser = auth.currentUser

    fun signupWithEmailAndPassword(email: String, password: String){
        if(currentUser == null){
            if(email.isNotEmpty() && password.isNotEmpty()){
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task -> }
            }
        }
    }*/
}