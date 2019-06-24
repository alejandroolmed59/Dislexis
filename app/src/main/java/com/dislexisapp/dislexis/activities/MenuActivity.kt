package com.dislexisapp.dislexis.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity;
import com.dislexisapp.dislexis.R
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
            R.id.action_logout ->{
                System.exit(0)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
/*
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(application)
        builder.setMessage("¿Seguro de cerrar sesion?")
        builder.setCancelable(true)
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.cancel()
        }

        builder.setPositiveButton("Exit") { _, _ ->
            finish()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    } TODO lo meti cuando presionara el de cerrar sesion pero se muere XD*/

}
