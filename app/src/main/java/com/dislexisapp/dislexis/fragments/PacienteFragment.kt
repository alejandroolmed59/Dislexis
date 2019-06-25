package com.dislexisapp.dislexis.fragments

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dislexisapp.dislexis.Adapters.TestAdapter
import com.dislexisapp.dislexis.AppConstants

import com.dislexisapp.dislexis.R
import com.dislexisapp.dislexis.network.UserRetro
import kotlinx.android.synthetic.main.fragment_paciente.*
import kotlinx.android.synthetic.main.fragment_paciente.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val ARG_PARAM1 = "user"
class PacienteFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var user: UserRetro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null){
            //Do whatever you need with the string here, like assign it to variable.
            user = savedInstanceState.getParcelable("user")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_paciente, container, false)
        val Testadapter = TestAdapter()
        Testadapter.setExamenes(user.examenes!!)
        view.rv_tests.apply {
            adapter = Testadapter
            layoutManager = LinearLayoutManager(this.context)
        }

        view.nombre.text = user.nombreCompleto
        view.email.text = user.email
        view.medicoRef.text = user.medicoReferencia

        if(user.examenes!!.size!=0) {
            var cont: Int = 0
            for (examen in user.examenes!!) {
                cont += examen.correctas!!.toInt()
            }
            view.tv_promedio.text = ((cont.toFloat() / user.examenes!!.size.toFloat()).toString())
        }else{
            view.tv_promedio.text= "0"
        }
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(user: UserRetro): PacienteFragment {
            val newFragment = PacienteFragment()
            newFragment.user = user
            return newFragment
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putParcelable("user", user)
        //declare values before saving the state
        super.onSaveInstanceState(savedInstanceState)
    }
}
