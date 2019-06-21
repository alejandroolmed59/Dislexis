package com.example.olmedo.dislexis.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.olmedo.dislexis.Network.Desafio
import com.example.olmedo.dislexis.Network.Examen
import com.example.olmedo.dislexis.R
import kotlinx.android.synthetic.main.fragment_desafio.*
import kotlinx.android.synthetic.main.fragment_desafio.view.*
import kotlinx.android.synthetic.main.fragment_pregunta.view.*


private const val ARG_PARAM1 = "desafio"


class DesafioFragment : Fragment() {

    private lateinit var desafio: Desafio

    private var listener: OnFragmentInteractionListener? = null


    fun newInstance(desafio: Desafio): DesafioFragment {
        val newFragment = DesafioFragment()
        newFragment.desafio = desafio
        return newFragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            desafio = it.getParcelable(ARG_PARAM1)
        }
    }

    interface OnFragmentInteractionListener {
        fun clickSiguienteDesafio(respuesta: String)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_desafio, container, false)
        Glide.with(view)
                .load(desafio.img)
                .override(600)
                .into(view.iv_Reto)
        view.respuesta1.text = desafio.respuesta1
        view.respuesta2.text = desafio.respuesta2
        view.respuesta3.text = desafio.respuesta3
        view.respuesta4.text = desafio.respuesta4

        view.respuesta1.setOnClickListener {
            listener?.clickSiguienteDesafio(desafio.respuesta1)
        }
        view.respuesta2.setOnClickListener {
            listener?.clickSiguienteDesafio(desafio.respuesta2)
        }
        view.respuesta3.setOnClickListener {
            listener?.clickSiguienteDesafio(desafio.respuesta3)
        }
        view.respuesta4.setOnClickListener {
            listener?.clickSiguienteDesafio(desafio.respuesta4)
        }

        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}

