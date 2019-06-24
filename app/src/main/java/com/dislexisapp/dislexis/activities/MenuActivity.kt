package com.dislexisapp.dislexis.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity;
import com.dislexisapp.dislexis.R
import kotlinx.android.synthetic.main.content_menu.*


class  MenuActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_menu)

        card_profile.setOnClickListener(){
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        card_diagnostic.setOnClickListener(){
            startActivity(Intent(this, DiagnosticActivity::class.java))
        }
        card_test.setOnClickListener(){
            startActivity(Intent(this, TestActivity::class.java))

        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/

}
