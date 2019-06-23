package com.example.olmedo.dislexis.Fragments

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.olmedo.dislexis.Network.Desafio
import com.example.olmedo.dislexis.Network.Examen
import com.example.olmedo.dislexis.R
import kotlinx.android.synthetic.main.fragment_desafio.*
import kotlinx.android.synthetic.main.fragment_desafio.view.*
import kotlinx.android.synthetic.main.fragment_pregunta.view.*


private const val ARG_PARAM1 = "desafio"


class DesafioFragment : Fragment(), View.OnDragListener, View.OnLongClickListener {

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
        fun clickValidarRespuesta(respuesta: String)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_desafio, container, false)
        Glide.with(view)
            .load(desafio.img)
            .override(600)
            .into(view.iv_Reto)
        view.respuesta1.text = desafio.respuesta1
        view.respuesta2.text = desafio.respuesta2
        view.respuesta3.text = desafio.respuesta3
        view.respuesta4.text = desafio.respuesta4


        view.respuesta1.tag = desafio.respuesta1
        view.respuesta2.tag = desafio.respuesta2
        view.respuesta3.tag = desafio.respuesta3
        view.respuesta4.tag = desafio.respuesta4


        view.respuesta1.setOnLongClickListener(this)
        view.respuesta2.setOnLongClickListener(this)
        view.respuesta3.setOnLongClickListener(this)
        view.respuesta4.setOnLongClickListener(this)

        view.tv_answer.setOnDragListener(this)

        view.respuesta1.setOnDragListener(this)
        view.respuesta2.setOnDragListener(this)
        view.respuesta3.setOnDragListener(this)
        view.respuesta4.setOnDragListener(this)



        view.btn_validate_answer.setOnClickListener {
            listener?.clickValidarRespuesta(view.tv_answer.text.toString())
        }

        view.btn_dynamic_siguiente.setOnClickListener {
            listener?.clickSiguienteDesafio(view.tv_answer.text.toString())
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

    fun auxClickValidarRespuesta(respuesta: Boolean){
        if(respuesta){
            tv_question_result.text = "Correct"
            tv_question_result.setTextColor(Color.parseColor("#00A025"))
            iv_question_result.setImageDrawable(resources.getDrawable(R.drawable.ic_checked))
        }else{
            tv_question_result.text = "Incorrect"
            tv_question_result.setTextColor(Color.parseColor("#C91200"))
            iv_question_result.setImageDrawable(resources.getDrawable(R.drawable.ic_cancel))
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDrag(v: View, event: DragEvent): Boolean {
        val action = event.action
        when (action) {

            DragEvent.ACTION_DRAG_STARTED -> {
                return event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                return true
            }

            DragEvent.ACTION_DRAG_LOCATION ->
                return true

            DragEvent.ACTION_DRAG_EXITED -> {
                return true
            }

            DragEvent.ACTION_DROP -> {
                val item = event.clipData.getItemAt(0)
                val dragData = item.text.toString()
                Log.v("DRAG", dragData)

                v.invalidate()

                val vw = event.localState as View
                val owner = vw.parent as ViewGroup


                val container = v as TextView
                container.text = dragData

                vw.visibility = View.VISIBLE
                return true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                return true
            }
            else -> Log.e("DragDrop Example", "Unknown action type received by OnDragListener.")
        }
        return false
    }

    override fun onLongClick(v: View): Boolean {
        val item = ClipData.Item(v.tag as CharSequence)

        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
        val data = ClipData(v.tag.toString(), mimeTypes, item)

        val dragshadow = View.DragShadowBuilder(v)

        v.startDrag(
            data        // data to be dragged
            , dragshadow   // drag shadow builder
            , v           // local data about the drag and drop operation
            , 0          // flags (not currently used, set to 0)
        )

        return true;
    }

}

