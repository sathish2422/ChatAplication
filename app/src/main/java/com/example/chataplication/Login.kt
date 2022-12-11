package com.example.chataplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth= FirebaseAuth.getInstance()

        edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        btnLogin=findViewById(R.id.btn_log)
        btnSignUp=findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener {
            val intent= Intent(this,Register::class.java)
            startActivity(intent )
        }
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password=edtPassword.text.toString()

            login(email,password)
        }

    }

    private fun login(email: String,password: String){
        //logic for login user

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for login in user

                    val intent= Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Login,"User Does N't Exist", Toast.LENGTH_SHORT).show()

                }
            }

    }
}