package com.example.olmedo.dislexis.Activities

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.olmedo.dislexis.Network.Examen
import com.example.olmedo.dislexis.Network.UserRetro
import com.example.olmedo.dislexis.R
import com.example.olmedo.dislexis.ViewModels.UserViewModel


class  menuActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    val preguntasList : MutableList<Examen> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_menu)

        val user: UserRetro? = intent.extras.getBundle("BUNDLE").getParcelable("USER")

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.getPreguntas()

        userViewModel.preguntasList.observe(this, Observer {
            preguntasList.addAll(it)
            //for(i in preguntasList){
              //  Log.v("examen", i.pregunta)
            //}
        })
    }

}
