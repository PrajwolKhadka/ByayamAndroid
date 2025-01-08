package com.example.byayamandroid.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.byayamandroid.R
import com.example.byayamandroid.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    lateinit var auth : FirebaseAuth
    lateinit var name: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()
        binding.signUp.setOnClickListener{
            var email= binding.registerEmail.text.toString()
            var password= binding.registerPassword.text.toString()

            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(
                        this@RegisterActivity, "Registration Success", Toast.LENGTH_LONG
                    ).show()
                }else{
                    Toast.makeText(
                        this@RegisterActivity, it.exception?.message, Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }
}