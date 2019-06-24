package com.dislexisapp.dislexis.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.dislexisapp.dislexis.database.entities.DTO.userAuthorization
import com.dislexisapp.dislexis.R
import com.dislexisapp.dislexis.viewModels.UserViewModel
import kotlinx.android.synthetic.main.register.*

class RegisterActivity : AppCompatActivity() {
    
    lateinit var userViewModel: UserViewModel
    lateinit var loadingBar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        Glide.with(this)
                .load(R.drawable.ic_register)
                .into(registerImage)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        rb_isPaciente.setOnClickListener() {
            medicoReferencia.setFocusable(true);
            medicoReferencia.setEnabled(true);
            medicoReferencia.setCursorVisible(true);

        }
        go_to_login.setOnClickListener() {
            startActivity(Intent(this, MainActivity::class.java))
        }

        if (rb_isPaciente.isChecked) {
            medicoReferencia.isEnabled = true
        } else if (!(rb_isPaciente.isChecked)) {
            medicoReferencia.isEnabled = false
        }
        bt_register.setOnClickListener() {
            var flag = if (rb_isPaciente.isChecked) {
                "true"
            } else {
                "false"
            }
            if (isNetworkAvailable()) {
                loadingBar = ProgressDialog(this)
                loadingBar.setTitle("Espere un momento...")
                loadingBar.setMessage("Estamos registrando sus datos")
                loadingBar.setCanceledOnTouchOutside(false)
                loadingBar.show()
                userViewModel.registerUser(
                        userAuthorization(
                                username.text.toString(),
                                tv_nombreCompleto.text.toString(),
                                email.text.toString(),
                                password.text.toString(),
                                flag,
                                medicoReferencia.text.toString()
                        ),
                        { callback: Boolean -> respuesta(callback) })
                userViewModel.code.observe(this, Observer { code ->
                    if (code == 500) {
                        loadingBar.dismiss()
                        Snackbar.make(it, "Complete todos los campos", Snackbar.LENGTH_LONG).show()
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

    }

    private fun respuesta(callback: Boolean) {
        loadingBar.dismiss()
        if (callback) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            // Toast.makeText(this, "Error medico inexistente o username ya en uso", Toast.LENGTH_LONG).show()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


}
