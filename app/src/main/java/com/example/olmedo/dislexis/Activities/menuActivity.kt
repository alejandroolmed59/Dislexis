package com.example.olmedo.dislexis.Activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.olmedo.dislexis.AppConstants
import com.example.olmedo.dislexis.Network.Examen
import com.example.olmedo.dislexis.Network.UserRetro
import com.example.olmedo.dislexis.PreguntaFragment
import com.example.olmedo.dislexis.R
import com.example.olmedo.dislexis.ViewModels.UserViewModel
import kotlinx.android.synthetic.main.content_menu.*


class  menuActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_menu)
        card_diagnostic.setOnClickListener(){
            startActivity(Intent(this, DiagnosticActivity::class.java))
        }
    }

}
