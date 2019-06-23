package com.dislexisapp.dislexis.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.dislexisapp.dislexis.R

import com.dislexisapp.dislexis.network.Paciente
import kotlinx.android.synthetic.main.cardview_paciente.view.*



class PacientesAdapter(val clickListener: (Paciente) -> Unit) : RecyclerView.Adapter<PacientesAdapter.ViewHolder>() {
    private var pacientes: List<Paciente> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_paciente, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = pacientes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(pacientes[position], clickListener)

    internal fun setPacientes(pacientes: List<Paciente>) {
        this.pacientes = pacientes
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(paciente: Paciente, clickListener: (Paciente) -> Unit) = with(itemView) {
            this.pacienteNombre.text = paciente.nombreCompleto
            this.setOnClickListener{
                clickListener(paciente)
            }
        }
    }
}