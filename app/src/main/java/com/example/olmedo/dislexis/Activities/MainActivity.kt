package com.example.olmedo.dislexis.Activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.example.olmedo.dislexis.Database.entities.DTO.userAuthorization
import com.example.olmedo.dislexis.Network.UserRetro
import com.example.olmedo.dislexis.R
import com.example.olmedo.dislexis.ViewModels.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)



        textViewLogin.setOnClickListener(){view ->
            if (isNetworkAvailable()) {
                userViewModel.loginUser(
                    editText.text.toString(),
                    editText2.text.toString(),
                    { user: UserRetro -> nuevaActivity(user) })
            }else{
                Snackbar.make(view, "No hay conexion a internet", Snackbar.LENGTH_LONG)
                    .setAction(
                        "OK",{it.setOnClickListener { Log.v("ok", "ok") }}
                    ).show()
            }
        }
        textViewRegister.setOnClickListener(){
            startActivity(Intent(this, registerActivity::class.java)) }
    }
    fun nuevaActivity(user: UserRetro){
        if(user!=null) {
            var bundle = Bundle()
            bundle.putParcelable("USER", user)
            startActivity(Intent(this, menuActivity::class.java).putExtra("BUNDLE", bundle))
        }
    }
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }




}
