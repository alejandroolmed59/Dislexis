package com.example.olmedo.dislexis.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.example.olmedo.dislexis.R
import kotlinx.android.synthetic.main.content_menu.*


class  menuActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_menu)

        card_diagnostic.setOnClickListener(){
            startActivity(Intent(this, DiagnosticActivity::class.java))
        }
        card_test.setOnClickListener(){
            startActivity(Intent(this, TestActivity::class.java))

        }
    }

}
