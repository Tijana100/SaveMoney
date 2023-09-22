package com.example.savemoney.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.savemoney.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginQa.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener{
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val confirmPass = binding.repassword.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if(pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener() {
                        if(it.isSuccessful){
                            val intent = Intent(this, LogInActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                }else {
                    Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty fields are not allowed", Toast.LENGTH_SHORT).show()

            }
        }
    }
}