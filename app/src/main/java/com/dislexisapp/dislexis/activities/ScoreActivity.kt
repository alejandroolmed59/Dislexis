package com.dislexisapp.dislexis.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dislexisapp.dislexis.R
import kotlinx.android.synthetic.main.diagnostic_score.*

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.diagnostic_score)
        val score = intent.getStringExtra("score")
        tv_score.text = score
        btGoMain.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }
}