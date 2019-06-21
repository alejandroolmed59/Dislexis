package com.example.olmedo.dislexis.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.olmedo.dislexis.AppConstants
import com.example.olmedo.dislexis.Fragments.DesafioFragment
import com.example.olmedo.dislexis.Network.Desafio
import com.example.olmedo.dislexis.R
import com.example.olmedo.dislexis.ViewModels.UserViewModel

class TestActivity : AppCompatActivity(), DesafioFragment.OnFragmentInteractionListener {

    var contadorRespuestasCorrectas : Int =0
    var contadorDesafio: Int =0
    val desafioList : MutableList<Desafio> = arrayListOf()
    val user = AppConstants.user
    private lateinit var mainFragment : DesafioFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.getDesafios()
        userViewModel.desafiosList.observe(this, Observer {
            desafioList.clear()
            desafioList.addAll(it)
            initMainFragment(contadorDesafio)
        })

    }
    fun initMainFragment(numDesafio: Int){
        mainFragment = DesafioFragment().newInstance(desafioList[numDesafio])
        val resource =  R.id.main_fragment
        changeFragment(resource, mainFragment)
    }


    private fun changeFragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }
    override fun clickSiguienteDesafio(respuesta: String) {
        if(respuesta== desafioList[contadorDesafio].respuestaCorrecta) contadorRespuestasCorrectas++
        contadorDesafio++
        if(desafioList.size>contadorDesafio) initMainFragment(contadorDesafio)
        else{ Toast.makeText(this, "Test completado: "+contadorRespuestasCorrectas+" correctas", Toast.LENGTH_LONG).show()}
    }
}

