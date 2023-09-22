package com.example.savemoney.view

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

object RegistrationUtil {
    private val auth = FirebaseAuth.getInstance()
    fun validateRegistrationInput(
        email: String,
        password:String,
        confirmedPassword: String
    ):Boolean{
        if(email.isEmpty() || password.isEmpty()){
            return false
        }
        if (!isValidEmail(email)){
            return false
        }
        if(password != confirmedPassword){
            return false
        }
        if(password.count{it.isDigit()} < 2){
            return false
        }
        return true
    }
    fun registerUser(email: String, password: String, confirmedPassword: String): Boolean {
        if (!validateRegistrationInput(email, password, confirmedPassword)) {
            return false
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                } else {
                    if (task.exception is FirebaseAuthUserCollisionException){

                    }
                }
            }
        return true
    }
    private fun isValidEmail(email: String): Boolean {
        return  android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}