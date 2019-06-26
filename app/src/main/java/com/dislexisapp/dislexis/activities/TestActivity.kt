package com.dislexisapp.dislexis.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dislexisapp.dislexis.utils.AppConstants
import com.dislexisapp.dislexis.fragments.DesafioFragment
import com.dislexisapp.dislexis.network.Desafio
import com.dislexisapp.dislexis.R
import com.dislexisapp.dislexis.viewModels.UserViewModel
import android.speech.tts.TextToSpeech

import java.util.*
import kotlin.collections.ArrayList


class TestActivity : AppCompatActivity(), DesafioFragment.OnFragmentInteractionListener {
    val limiteDePreguntas: Int = 2

    var contadorRespuestasCorrectas: Int = 0
    var contadorDesafio: Int = 0
    var desafioList: MutableList<Desafio> = arrayListOf()
    val user = AppConstants.user
    private lateinit var mainFragment: DesafioFragment
    lateinit var mTTS: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                //if there is no error then set language
                mTTS.language = Locale.getDefault()
            }
        })
        if (savedInstanceState != null){
            desafioList = savedInstanceState.getParcelableArrayList("list")
            contadorDesafio = savedInstanceState.getInt("contadorDesafio")
            contadorRespuestasCorrectas = savedInstanceState.getInt("contadorRespuestasCorrectas")
            initMainFragment(contadorDesafio)
        }else {
            userViewModel.getDesafios()
            userViewModel.desafiosList.observe(this, Observer {
                it.shuffle()
                desafioList.clear()
                desafioList.addAll(it.subList(0, limiteDePreguntas))
                initMainFragment(contadorDesafio)
            })
        }
    }

    fun initMainFragment(numDesafio: Int) {

        mTTS.speak(desafioList[numDesafio].respuestaCorrecta, TextToSpeech.QUEUE_FLUSH, null)
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

        if (respuesta != resources.getString(R.string.txt_answer_initial)) {
            if (respuesta == desafioList[contadorDesafio].respuestaCorrecta) contadorRespuestasCorrectas++

            if (desafioList.size > contadorDesafio + 1) {
                Log.v("contador1", contadorDesafio.toString())
                contadorDesafio++
                Log.v("contador2", contadorDesafio.toString())
                initMainFragment(contadorDesafio)
            } else {
                val intent =
                    Intent(this, ScoreActivity::class.java).putExtra("score", contadorRespuestasCorrectas.toString())
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent)
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
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.apply {
            putParcelableArrayList("list", ArrayList(desafioList))
            putInt("contadorDesafio", contadorDesafio)
            putInt("contadorRespuestasCorrectas", contadorRespuestasCorrectas)
        }

        //declare values before saving the state
        super.onSaveInstanceState(savedInstanceState)
    }

}

