package com.example.olmedo.dislexis.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.olmedo.dislexis.AppConstants
import com.example.olmedo.dislexis.Network.Examen
import com.example.olmedo.dislexis.Fragments.PreguntaFragment
import com.example.olmedo.dislexis.R
import com.example.olmedo.dislexis.ViewModels.UserViewModel

class DiagnosticActivity : AppCompatActivity(), PreguntaFragment.OnFragmentInteractionListener {

    var contadorPregunta: Int =0
    var contadorRespuestaCorrecta: Int =0
    lateinit var userViewModel: UserViewModel
    val preguntasList : MutableList<Examen> = arrayListOf()
    val user = AppConstants.user
    private lateinit var mainFragment : PreguntaFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnostic)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.getPreguntas()

        userViewModel.preguntasList.observe(this, Observer {
            preguntasList.clear()
            preguntasList.addAll(it)
            initMainFragment(contadorPregunta)
        })
    }
    override fun clickSiguientePregunta(respuesta: String) {
        if(respuesta == "correcta") contadorRespuestaCorrecta++
        contadorPregunta++
        if(preguntasList.size> contadorPregunta) initMainFragment(contadorPregunta)
        else { if(user!=null) {
            userViewModel.subirExamen(user.username!!, contadorRespuestaCorrecta )
            startActivity(Intent(this, ScoreActivity::class.java).putExtra("score", contadorRespuestaCorrecta.toString()))
        } }


    }

    fun initMainFragment(numPregunta: Int){
        mainFragment = PreguntaFragment.newInstance(preguntasList[numPregunta])
        val resource =  R.id.main_fragment
        changeFragment(resource, mainFragment)
    }


    private fun changeFragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }
}
