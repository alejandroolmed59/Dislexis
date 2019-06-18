package com.example.olmedo.dislexis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.olmedo.dislexis.Database.entities.User
import com.example.olmedo.dislexis.Network.UserRetro
import com.example.olmedo.dislexis.ViewModels.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)



        textViewLogin.setOnClickListener(){
            userViewModel.loginUser(editText.text.toString(), editText2.text.toString())

/*
            userViewModel.userActual.observe(this, Observer {user->
                if(user!=null) {
                    var bundle = Bundle()
                    bundle.putParcelable("USER", user)
                    startActivity(Intent(this, menuActivity::class.java).putExtra("BUNDLE", bundle))
                }
            }) */
        }
    }



}
