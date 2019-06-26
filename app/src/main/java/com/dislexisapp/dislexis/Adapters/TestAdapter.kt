package com.dislexisapp.dislexis.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dislexisapp.dislexis.R

import com.dislexisapp.dislexis.network.ExamenRetro
import com.dislexisapp.dislexis.utils.AppConstants
import kotlinx.android.synthetic.main.cardview_test.view.*


class TestAdapter internal  constructor() : RecyclerView.Adapter<TestAdapter.ViewHolder>() {
    private var examenes: List<ExamenRetro> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_test, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = examenes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(examenes[position])

    internal fun setExamenes(examenes: List<ExamenRetro>) {
        this.examenes = examenes
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(examen: ExamenRetro) = with(itemView) {
            if(AppConstants.rolOriginal== "PacienteDef") {
                val total: Double = (examen.correctas.toFloat() / AppConstants.limiteDeDesafios.toFloat()).toDouble()
                var cadena = when (total) {
                    in 0.0..0.099 -> "Puedes hacerlo mucho mejor!!"
                    in 0.1..0.4 -> "Puedes hacerlo mejor!"
                    in 0.41..0.7 -> "Vamos mejorando"
                    in 0.71..0.99 -> "Vamos muy bien!"
                    1.0 -> "Lo hiciste excelente!!"
                    else -> "Def"
                }
                puntuacion.text = cadena
            }else {
                puntuacion.text = examen.correctas
            }
        }
    }
}