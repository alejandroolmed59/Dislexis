package com.example.olmedo.dislexis.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.olmedo.dislexis.R
import java.lang.Exception

class splashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background = object : Thread(){
            override fun run() {
                try {
                    Thread.sleep(2000)
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
