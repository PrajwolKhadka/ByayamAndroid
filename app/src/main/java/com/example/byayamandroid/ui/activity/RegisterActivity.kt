package com.example.byayamandroid.ui.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.byayamandroid.R
import com.example.byayamandroid.databinding.ActivityRegisterBinding
import com.example.byayamandroid.model.UserModel
import com.example.byayamandroid.repository.UserRepositoryImpl
import com.example.byayamandroid.utils.LoadingUtils
import com.example.byayamandroid.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    lateinit var userViewModel: UserViewModel

    lateinit var loadingUtils: LoadingUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingUtils=LoadingUtils(this)

        var repo= UserRepositoryImpl()
        userViewModel= UserViewModel(repo)
        binding.signUp.setOnClickListener{
            loadingUtils.show()
            var email= binding.registerEmail.text.toString()
            var password= binding.registerPassword.text.toString()
            var firstName= binding.registerFname.text.toString()
            var lastName= binding.registerLName.text.toString()
            var address= binding.registerAddress.text.toString()
            var contact= binding.registerContact.text.toString()

            userViewModel.signup(email, password){
                    success,message,userId->
                if(success){
                    var userModel = UserModel(
                        userId.toString(),
                        firstName,
                        lastName,
                        address,
                        contact,
                        email
                    )

                    userViewModel.addUserToDatabase(userId,userModel){
                            success,message->
                        if(success){
                            loadingUtils.dismiss()
                            Toast.makeText(
                                this@RegisterActivity,
                                message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }else{
                    loadingUtils.dismiss()
                    Toast.makeText(this@RegisterActivity,message,Toast.LENGTH_LONG).show()
                }
            }
//            auth.createUserWithEmailAndPassword(email,password)
//                .addOnCompleteListener{
//                if(it.isSuccessful){
//                    var userId= auth.currentUser?.uid
//
//                    var userModel= UserModel(
//                        userId.toString(),firstName,lastName,address,contact,email
//                    )
//                    ref.child(userId.toString()).setValue(userModel).addOnCompleteListener(
//                        {
//                            if(it.isSuccessful){
//                                Toast.makeText(
//                                    this@RegisterActivity, "Registration Success", Toast.LENGTH_LONG
//                                ).show()
//                            }
//                        }
//                    )
//                }else{
//                    Toast.makeText(
//                        this@RegisterActivity, it.exception?.message, Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }
}}