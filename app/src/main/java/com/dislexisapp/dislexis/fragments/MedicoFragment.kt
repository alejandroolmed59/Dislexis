package com.dislexisapp.dislexis.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dislexisapp.dislexis.Adapters.PacientesAdapter

import com.dislexisapp.dislexis.R
import com.dislexisapp.dislexis.network.Paciente
import kotlinx.android.synthetic.main.fragment_medico.view.*

private const val ARG_PARAM1 = "pac"



class MedicoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var pacientes: List<Paciente> = emptyList()
    private var listener: Listener? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_medico, container, false)
        val adapterPac = PacientesAdapter({paciente: Paciente -> listener?.clickPaciente(paciente) })
        adapterPac.setPacientes(pacientes)
        view.rv_pacientes.apply{
            adapter= adapterPac
            layoutManager = LinearLayoutManager(this.context)
        }
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface Listener {
        fun clickPaciente(paciente: Paciente)
    }

    companion object {
        @JvmStatic
        fun newInstance(pacientes: List<Paciente>): MedicoFragment {
            val newFragment = MedicoFragment()
            newFragment.pacientes = pacientes
            return newFragment
        }
    }
}
