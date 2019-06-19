package com.example.olmedo.dislexis.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.olmedo.dislexis.AppConstants
import com.example.olmedo.dislexis.Database.entities.DTO.userAuthorization
import com.example.olmedo.dislexis.Network.UserRetro
import com.example.olmedo.dislexis.R
import com.example.olmedo.dislexis.ViewModels.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)



        textViewLogin.setOnClickListener(){
            userViewModel.loginUser(editText.text.toString(), editText2.text.toString(), {user: UserRetro-> nuevaActivity(user)})

        }
        textViewRegister.setOnClickListener(){
            startActivity(Intent(this, registerActivity::class.java)) }
    }
    fun nuevaActivity(user: UserRetro){
            AppConstants.user = user
            startActivity(Intent(this, menuActivity::class.java))
    }
}
