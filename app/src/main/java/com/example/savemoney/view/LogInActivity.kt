package com.example.savemoney.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.savemoney.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private  lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.signhere.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        binding.login.setOnClickListener{
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() ){
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener() {
                    if(it.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }

    }

 /*override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
}*/
}
