package com.example.olmedo.dislexis.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.olmedo.dislexis.Database.RoomDB
import com.example.olmedo.dislexis.Database.repositories.Repository
import com.example.olmedo.dislexis.Database.entities.DTO.userAuthorization
import com.example.olmedo.dislexis.Database.entities.User
import com.example.olmedo.dislexis.Database.entities.UserLogged
import com.example.olmedo.dislexis.Network.UserRetro
import com.example.olmedo.dislexis.Network.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val app: Application) : AndroidViewModel(app) {

    private val repository: Repository
    //lateinit var userActual: MutableLiveData<UserRetro>

    init {
        val userDao = RoomDB.getInstance(app).userDao()
        val userService = UserService.getUserService()
       // userActual.postValue(UserRetro())
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
        val response = repository.loginUser(
            userAuthorization(
                user,
                password,
                null,
                null
            )
        ).await()
        if (response.isSuccessful) with(response) {
            if (this.code() == 200) {
                Log.v("statusUser", "succ")
                val responseUser = repository.getUser(UserLogged(user)).await()
                if(responseUser.isSuccessful) with(responseUser){
                    if(this.code() == 200){
                        Log.v("respuestaGetUser", this.body()!!._id)
                        Log.v("respuestaGetUser", this.body()!!.email)
                        Log.v("respuestaGetUser", this.body()!!.isPaciente)
                        Log.v("respuestaGetUser", this.body()!!.medicoReferencia)
                        Log.v("respuestaGetUser", this.body()!!.username)
                        //userActual.postValue(response.body())
                    }
                }else{

                }
            } else {
                Log.v("statusUser", "fail")
            }
        } else {
            Log.v("statusUser", "fail")
        }
    }
/*
    private fun getUserAux(username: String) =  viewModelScope.launch(Dispatchers.IO) {
        val response = repository.getUser(UserLogged(username)).await()
        if(response.isSuccessful) with(response){
            if(this.code() == 200){
                Log.v("respuestaGetUser", this.body()!!._id)
                Log.v("respuestaGetUser", this.body()!!.email)
                Log.v("respuestaGetUser", this.body()!!.isPaciente)
                Log.v("respuestaGetUser", this.body()!!.medicoReferencia)
                Log.v("respuestaGetUser", this.body()!!.username)
                //userActual.postValue(response.body())
            }
        }
    }
    */
}

