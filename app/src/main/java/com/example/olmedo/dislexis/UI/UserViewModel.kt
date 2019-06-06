package com.example.olmedo.dislexis.UI

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.olmedo.dislexis.Database.RoomDB
import com.example.olmedo.dislexis.Entities.DTO.userAuthorization
import com.example.olmedo.dislexis.Entities.User
import com.example.olmedo.dislexis.Retrofit.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val app: Application) : AndroidViewModel(app) {

    private val repository: Repository

    init {
        val userDao = RoomDB.getInstance(app).userDao()
        val userService = UserService.getUserService()
        repository = Repository(userDao, userService)
    }

    private suspend fun insert(user: User) = repository.insert(user)


    fun registerUser(userAuthorization: userAuthorization) = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.registerUser(userAuthorization).await()
        if (response.isSuccessful) with(response) {
            if (this.code() == 200) {
                Log.v("status", "succes")
                //Toast.makeText(app, "Register exitoso", Toast.LENGTH_LONG).show()
            } else {
                Log.v("status", "fail")
            }
        } else {
            Log.v("status", "fail")
            // Toast.makeText(app, "Error con el registro", Toast.LENGTH_LONG).show()
        }
    }

    fun loginUser(user: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.loginUser(userAuthorization(user, password, null, null)).await()
        if (response.isSuccessful) with(response) {
            if (this.code() == 200) {
                Log.v("status", "succes")
                //Toast.makeText(app, "Register exitoso", Toast.LENGTH_LONG).show()
            } else {
                Log.v("status", "fail")
            }
        } else {
            Log.v("status", "fail")
            // Toast.makeText(app, "Error con el registro", Toast.LENGTH_LONG).show()
        }
    }
}

