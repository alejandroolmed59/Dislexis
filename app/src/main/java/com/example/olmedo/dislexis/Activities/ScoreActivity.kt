package com.example.olmedo.dislexis.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.olmedo.dislexis.R
import kotlinx.android.synthetic.main.diagnostic_score.*

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.diagnostic_score)
        //val score = intent.getIntExtra("score")
        //tv_score.text = score.toString()
    }
}
