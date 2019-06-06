package com.example.olmedo.dislexis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.olmedo.dislexis.Entities.User
import com.example.olmedo.dislexis.R
import com.example.olmedo.dislexis.UI.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        textViewLogin.setOnClickListener(){
            userViewModel.loginUser(editText.text.toString(), editText2.text.toString())
        }
    }
}
