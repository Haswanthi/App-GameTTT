package com.example.gamettt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class signupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var edtUsername: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupactivity)

        auth = FirebaseAuth.getInstance()
        edtUsername = findViewById(R.id.edt_signup_username)
        edtEmail = findViewById(R.id.edt_signup_email)
        edtPassword = findViewById(R.id.edt_signup_password)
        btnSignUp = findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener{
            signUpUser(
                email = edtEmail.text.toString(),
                password = edtPassword.text.toString()

            )
        }
    }

    private fun signUpUser(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(
                        Intent(
                            this, homeActivity::class.java
                        )
                    )
                    finish()
                } else {
                    Toast.makeText(
                        this, "Registration failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }
}