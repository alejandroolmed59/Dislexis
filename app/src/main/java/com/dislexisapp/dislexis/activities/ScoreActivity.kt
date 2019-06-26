package com.dislexisapp.dislexis.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dislexisapp.dislexis.R
import kotlinx.android.synthetic.main.diagnostic_score.*

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.diagnostic_score)
        val score = intent.getStringExtra("score").toFloat()
        val limite = intent.getStringExtra("limite").toFloat()
        val total: Double = (score/limite).toDouble()

        Log.v("total", total.toString())

        var cadena = when(total){
            in 0.0 .. 0.099 -> "Puedes hacerlo mucho mejor!!"
            in 0.1 .. 0.4 -> "Puedes hacerlo mejor!"
            in 0.41 .. 0.7 -> "Vamos mejorando"
            in 0.71 .. 0.99 -> "Vamos muy bien!"
            1.0 -> "Lo hiciste excelente!!"
            else -> "Def"
        }
        tv_score.text = cadena
        btGoMain.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}