package com.example.olmedo.dislexis.Activities

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.example.olmedo.dislexis.Network.UserRetro
import com.example.olmedo.dislexis.R

import kotlinx.android.synthetic.main.activity_menu.*

class  menuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val user: UserRetro? = intent.extras.getBundle("BUNDLE").getParcelable("USER")
        username.text = user!!.username
        Log.v("menuAct", user!!.username)
        Log.v("menuAct", user!!._id)
    }

}
