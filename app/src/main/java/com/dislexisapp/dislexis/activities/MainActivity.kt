package com.dislexisapp.dislexis.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dislexisapp.dislexis.AppConstants
import com.dislexisapp.dislexis.network.UserRetro
import com.dislexisapp.dislexis.R
import com.dislexisapp.dislexis.viewModels.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    lateinit var loadingBar : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        textViewLogin.setOnClickListener(){
            if (isNetworkAvailable()) {
                //Adding progress bar dialog for better UI experience
                loadingBar = ProgressDialog(this)
                loadingBar.setTitle("Espere un momento...")
                loadingBar.setMessage("Estamos cargando sus datos")
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
            startActivity(Intent(this, RegisterActivity::class.java)) }
        goToAboutUs.setOnClickListener(){
            startActivity(Intent(this, AboutActivity::class.java ))
        }
    }
    fun nuevaActivity(user: UserRetro){
            AppConstants.user= user
            loadingBar.dismiss()
            startActivity(Intent(this, MenuActivity::class.java))
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


}
