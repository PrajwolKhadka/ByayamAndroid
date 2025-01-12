package com.example.byayamandroid.utils

import android.app.Activity
import android.app.AlertDialog

class LoadingUtils(val activity: Activity) {
    lateinit var alertDialog: AlertDialog

    fun show(){

    }
    fun dismiss(){
        alertDialog.dismiss()
    }
}