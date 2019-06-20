package com.example.olmedo.dislexis

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.olmedo.dislexis.Network.Examen
import kotlinx.android.synthetic.main.fragment_pregunta.*
import kotlinx.android.synthetic.main.fragment_pregunta.view.*


private const val ARG_PARAM1 = "ex"


class PreguntaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var pregunta: Examen

    private var listener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        fun clickSiguientePregunta(respuesta : String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pregunta = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_pregunta, container, false)

            //Log.v("fragment", notoquenesto.notoquenesto + notoquenesto.respuesta)
        //    view.tv_pregunta.text = pregunta.pregunta
          //  view.correcta.text = pregunta.respuesta
            //view.incorrecta.text= pregunta.respuestaIncorrecta

            //view.correcta.setOnClickListener(){
             //   listener?.clickSiguientePregunta("correcta")
            //}
            //view.incorrecta.setOnClickListener(){
            //listener?.clickSiguientePregunta("incorrecta")
            //}
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



    companion object {
        @JvmStatic
        fun newInstance(pregunta: Examen): PreguntaFragment{
            val newFragment = PreguntaFragment()
            newFragment.pregunta = pregunta
            return newFragment
        }
    }
}
