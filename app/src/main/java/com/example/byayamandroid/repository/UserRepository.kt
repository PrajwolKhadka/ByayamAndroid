package com.example.byayamandroid.repository

import com.example.byayamandroid.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    //    {
//       "success":true
//       "message": "login successful"
//    }

    fun login(email:String, password:String , callback:(Boolean,String)->Unit)

    fun signup(email:String, password:String , callback:(Boolean,String, String)->Unit)
//    {
//       "success":true
//       "message": "register successful"
//        "userId": "1234"
//    }

    fun addUserToDatabase(userId:String,userModel: UserModel, callback:(Boolean,String)->Unit)

    fun forgetPassword(email:String, callback:(Boolean,String)->Unit)

    fun getCurrentUser(): FirebaseUser?
}