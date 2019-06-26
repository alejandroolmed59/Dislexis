package com.dislexisapp.dislexis.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dislexisapp.dislexis.R
import com.dislexisapp.dislexis.utils.AppConstants
import com.dislexisapp.dislexis.utils.SaveSharedPreference
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(1500)
                    lateinit var intent: Intent
                    if (SaveSharedPreference.getLoggedStatus(applicationContext) && AppConstants.user != null) {
                        intent = Intent(applicationContext, MenuActivity::class.java)
                    } else {
                        intent = Intent(baseContext, MainActivity::class.java)
                    }
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
