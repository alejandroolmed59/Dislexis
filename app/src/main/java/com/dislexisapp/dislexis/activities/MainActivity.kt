package com.dislexisapp.dislexis.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dislexisapp.dislexis.utils.AppConstants
import com.dislexisapp.dislexis.network.UserRetro
import com.dislexisapp.dislexis.R
import com.dislexisapp.dislexis.utils.AppConstants.PASSWORD_PREF
import com.dislexisapp.dislexis.utils.AppConstants.USER_PREF
import com.dislexisapp.dislexis.utils.SaveSharedPreference
import com.dislexisapp.dislexis.viewModels.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    lateinit var loadingBar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        if (!SaveSharedPreference.getLoggedStatus(applicationContext)) {
            rl_login.visibility = View.VISIBLE
        }

        if (SaveSharedPreference.getLoggedStatus(applicationContext) && AppConstants.user == null) {
            if (isNetworkAvailable()) {
                loadingBar = ProgressDialog(this)
                loadingBar.setTitle("Espere un momento...")
                loadingBar.setMessage("Estamos cargando sus datos")
                loadingBar.setCanceledOnTouchOutside(false)
                loadingBar.show()

                val user = SaveSharedPreference.getUsername(this)
                val pass = SaveSharedPreference.getPassword(this)
                userViewModel.loginUser(
                    user!!,
                    pass!!,
                    { user: UserRetro -> nuevaActivity(user) })
            } else {
                Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_LONG).show()
            }
        }

        textViewLogin.setOnClickListener() {
            if (isNetworkAvailable()) {
                //Adding progress bar dialog for better UI experience
                loadingBar = ProgressDialog(this)
                loadingBar.setTitle("Espere un momento...")
                loadingBar.setMessage("Estamos cargando sus datos")
                loadingBar.setCanceledOnTouchOutside(false)
                loadingBar.show()
                SaveSharedPreference.setUsername(applicationContext, editText.text.toString())
                SaveSharedPreference.setPassword(applicationContext, editText2.text.toString())
                userViewModel.loginUser(
                    editText.text.toString(),
                    editText2.text.toString(),
                    { user: UserRetro -> nuevaActivity(user) })


                userViewModel.code.observe(this, Observer { code ->
                    if (code == 500) {
                        loadingBar.dismiss()
                        Snackbar.make(it, "Datos erroneos", Snackbar.LENGTH_LONG).show()
                        //userViewModel.code.postValue(0)
                    }
                    userViewModel.code.postValue(0)
                })

            } else {
                Snackbar.make(it, "No hay conexion a internet", Snackbar.LENGTH_LONG)
                    .setAction(
                        "OK", { it.setOnClickListener { Log.v("ok", "ok") } }
                    ).show()
            }

        }
        textViewRegister.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }
        goToAboutUs.setOnClickListener() {
            val intent = Intent(this, AboutActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }
    }

    fun nuevaActivity(user: UserRetro) {
        AppConstants.user = user
        loadingBar.dismiss()
        SaveSharedPreference.setLoggedIn(applicationContext, true)
        val intent = Intent(this, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


}
