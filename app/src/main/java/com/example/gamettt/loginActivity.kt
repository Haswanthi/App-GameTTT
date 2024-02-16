package com.example.gamettt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button

    private lateinit var txtCreateAccount: TextView
    private lateinit var txtResetPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        auth = FirebaseAuth.getInstance()
        edtEmail = findViewById(R.id.edt_login_email)
        edtPassword = findViewById(R.id.edt_login_password)
        btnLogin = findViewById(R.id.btn_login)
        txtCreateAccount = findViewById(R.id.txt_create_account)
        txtResetPassword = findViewById(R.id.txt_reset_password)

        btnLogin.setOnClickListener {
            logInUser(
                email = edtEmail.text.toString(),
                password = edtPassword.text.toString()
            )
        }

        txtCreateAccount.setOnClickListener{
            startActivity(
                Intent(
                    this@loginActivity, signupActivity::class.java
                )
            )
        }

        txtResetPassword.setOnClickListener{
            startActivity(
                Intent(
                    this@loginActivity, resetActivity::class.java
                )
            )
        }

    }

    private fun logInUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(
                        Intent(
                            this, homeActivity::class.java
                        )
                    )
                    finish()
                } else {
                    Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
