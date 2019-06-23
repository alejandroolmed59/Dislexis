package com.dislexisapp.dislexis.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dislexisapp.dislexis.AppConstants
import com.dislexisapp.dislexis.fragments.DesafioFragment
import com.dislexisapp.dislexis.network.Desafio
import com.dislexisapp.dislexis.R
import com.dislexisapp.dislexis.viewModels.UserViewModel

class TestActivity : AppCompatActivity(), DesafioFragment.OnFragmentInteractionListener {

    var contadorRespuestasCorrectas: Int = 0
    var contadorDesafio: Int = 0
    val desafioList: MutableList<Desafio> = arrayListOf()
    val user = AppConstants.user
    private lateinit var mainFragment: DesafioFragment

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

    fun initMainFragment(numDesafio: Int) {
        mainFragment = DesafioFragment().newInstance(desafioList[numDesafio])
        val resource = R.id.main_fragment
        changeFragment(resource, mainFragment)
    }


    private fun changeFragment(id: Int, frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }

    override fun clickValidarRespuesta(respuesta: String) {
        val bool = respuesta == desafioList[contadorDesafio].respuestaCorrecta
        mainFragment.auxClickValidarRespuesta(bool)
    }

    override fun clickSiguienteDesafio(respuesta: String) {
        Log.d("RESOURCE", resources.getString(R.string.txt_answer_initial))
        Log.d("RESPUESTAVACIA", respuesta)

        if (respuesta != resources.getString(R.string.txt_answer_initial)) {
            if (respuesta == desafioList[contadorDesafio].respuestaCorrecta) contadorRespuestasCorrectas++

            if (desafioList.size > contadorDesafio+1) {
                Log.v("contador1", contadorDesafio.toString())
                contadorDesafio++
                Log.v("contador2", contadorDesafio.toString())
                initMainFragment(contadorDesafio)
            } else {
                Toast.makeText(
                    this,
                    "Test completado: " + contadorRespuestasCorrectas + " correctas",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        } else {
            Toast.makeText(
                this,
                "No ha contestado la pregunta",
                Toast.LENGTH_LONG
            )
                .show()
        }
    }
}

