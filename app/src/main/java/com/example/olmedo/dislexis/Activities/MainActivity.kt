package com.example.olmedo.dislexis.Activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.olmedo.dislexis.AppConstants
import com.example.olmedo.dislexis.Database.entities.DTO.userAuthorization
import com.example.olmedo.dislexis.Network.UserRetro
import com.example.olmedo.dislexis.R
import com.example.olmedo.dislexis.ViewModels.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.register.*

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    lateinit var loadingBar : ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme) //added for spalsh screen

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)




        textViewLogin.setOnClickListener(){
            if (isNetworkAvailable()) {
                //Adding progress bar dialog for better UI experience
                loadingBar = ProgressDialog(this)
                loadingBar.setTitle("Please wait...")
                loadingBar.setMessage("We are charging your data")
                loadingBar.setCanceledOnTouchOutside(false)
                loadingBar.show()
                userViewModel.loginUser(
                    editText.text.toString(),
                    editText2.text.toString(),
                    {user: UserRetro-> nuevaActivity(user)})

                userViewModel.code.observe(this, Observer{code ->
                    if(code == 500){
                        loadingBar.dismiss()
                        Snackbar.make(it,"Datos erroneos",Snackbar.LENGTH_LONG).show()
                        //userViewModel.code.postValue(0)
                    }
                    userViewModel.code.postValue(0)
                })

            }else{
                Snackbar.make(it, "No hay conexion a internet", Snackbar.LENGTH_LONG)
                    .setAction(
                        "OK",{it.setOnClickListener { Log.v("ok", "ok") }}
                    ).show()
            }

        }
        textViewRegister.setOnClickListener(){
            startActivity(Intent(this, registerActivity::class.java)) }
        goToAboutUs.setOnClickListener(){
            startActivity(Intent(this, About::class.java ))
        }
    }
    fun nuevaActivity(user: UserRetro){
        if(user!=null) {
            AppConstants.user= user
            loadingBar.dismiss()
            startActivity(Intent(this, menuActivity::class.java))
        }

    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }





}
