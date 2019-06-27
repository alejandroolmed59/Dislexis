package com.dislexisapp.dislexis.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dislexisapp.dislexis.network.Examen
import com.dislexisapp.dislexis.R
import kotlinx.android.synthetic.main.fragment_pregunta.view.*



class PreguntaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var pregunta: Examen

    private var listener: OnFragmentInteractionListener? = null

    interface OnFragmentInteractionListener {
        fun clickSiguientePregunta(respuesta : String)
        fun repetirPregunta()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null){
            //Do whatever you need with the string here, like assign it to variable.
            pregunta = savedInstanceState.getParcelable("pregunta")
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putParcelable("pregunta", pregunta)
        //declare values before saving the state
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_pregunta, container, false)

            view.pregunta.text = pregunta.pregunta
            view.tv_respuesta1.text = pregunta.respuesta1
            view.tv_respuesta2.text= pregunta.respuesta2

            view.pregunta.setOnClickListener{
                listener?.repetirPregunta()
            }
            view.ly_respuesta1.setOnClickListener{
                listener?.clickSiguientePregunta(pregunta.respuesta1!!)
            }
            view.ly_respuesta2.setOnClickListener{
                listener?.clickSiguientePregunta(pregunta.respuesta2!!)
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

    companion object {
        @JvmStatic
        fun newInstance(pregunta: Examen): PreguntaFragment {
            val newFragment = PreguntaFragment()
            newFragment.pregunta = pregunta
            return newFragment
        }
    }
}
