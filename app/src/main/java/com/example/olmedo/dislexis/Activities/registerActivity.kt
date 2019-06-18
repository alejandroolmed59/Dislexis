package com.example.olmedo.dislexis.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders
import com.example.olmedo.dislexis.Database.entities.DTO.userAuthorization
import com.example.olmedo.dislexis.R
import com.example.olmedo.dislexis.ViewModels.UserViewModel
import kotlinx.android.synthetic.main.activity_register.*

class registerActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        login.setOnClickListener(){
            userViewModel.registerUser(userAuthorization(username.text.toString(), email.text.toString(), password.text.toString(), isPaciente.text.toString(), medicoReferencia.text.toString()), {callback: Boolean-> respuesta(callback)})
        }
    }

    private fun respuesta(callback: Boolean) {
        if(callback){
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            Toast.makeText(this, "Error medico inexistente o username ya en uso", Toast.LENGTH_LONG).show()
        }
    }


}
