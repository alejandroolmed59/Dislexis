package com.dislexisapp.dislexis.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.dislexisapp.dislexis.database.entities.DTO.userAuthorization
import com.dislexisapp.dislexis.R
import com.dislexisapp.dislexis.viewModels.UserViewModel
import kotlinx.android.synthetic.main.register.*
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    lateinit var loadingBar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        rb_isPaciente.setOnClickListener() {
            medicoReferencia.setFocusable(true);
            medicoReferencia.setEnabled(true);
            medicoReferencia.setCursorVisible(true);

        }
        go_to_login.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        email.validate({ s -> s.isValidEmail() },
            "Se requiere un email valido")

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
                var emailA : EditText = findViewById(R.id.email)



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

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    fun EditText.validate(validator: (String) -> Boolean, message: String) {
        this.afterTextChanged {
            this.error = if (validator(it)) null else message
        }
        this.error = if (validator(this.text.toString())) null else message
    }

    fun String.isValidEmail(): Boolean
            = this.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
