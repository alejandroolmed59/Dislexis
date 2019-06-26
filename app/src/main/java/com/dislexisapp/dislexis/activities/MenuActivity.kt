package com.dislexisapp.dislexis.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity;
import com.dislexisapp.dislexis.R
import com.dislexisapp.dislexis.utils.SaveSharedPreference
import kotlinx.android.synthetic.main.content_menu.*


class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_menu)

        card_profile.setOnClickListener() {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        card_diagnostic.setOnClickListener() {
            val intent = Intent(this, DiagnosticActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }
        card_test.setOnClickListener() {
            val intent = Intent(this, TestActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                SaveSharedPreference.setLoggedIn(applicationContext, false)
                SaveSharedPreference.setUsername(applicationContext,"")
                SaveSharedPreference.setPassword(applicationContext,"")
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                //System.exit(0)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
