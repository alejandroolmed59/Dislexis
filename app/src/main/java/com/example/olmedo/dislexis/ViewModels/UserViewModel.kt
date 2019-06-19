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
import com.example.olmedo.dislexis.Network.Examen
import com.example.olmedo.dislexis.Network.UserRetro
import com.example.olmedo.dislexis.Network.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val app: Application) : AndroidViewModel(app) {

    private val repository: Repository

    init {
        val userDao = RoomDB.getInstance(app).userDao()
        val userService = UserService.getUserService()
        repository = Repository(userDao, userService)
    }

    val preguntasList = MutableLiveData<MutableList<Examen>>()

    private suspend fun insert(user: User) = repository.insert(user)


    fun registerUser(userAuthorization: userAuthorization, callback: (Boolean)->Unit) = viewModelScope.launch(Dispatchers.IO) {
        Log.v("LLEGO", "1")
        val response = repository.registerUser(userAuthorization).await()

        if (response.isSuccessful) with(response) {
            if (this.code() == 200) {
                Log.v("registerStatus", "succes")
//                Log.v("registerStatus", this.body()?.username)
                callback(true)
            } else {
                callback(false)
                Log.v("registerStatus", "fail")
            }
        } else {
            callback(false)
            Log.v("registerStatus", "fail")
            // Toast.makeText(app, "Error con el registro", Toast.LENGTH_LONG).show()
        }
    }

    fun loginUser(user: String, password: String, callback: (UserRetro)->Unit) = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.loginUser(
            userAuthorization(
                user,
                null,
                password,
                null,
                null
            )
        ).await()
        if (response.isSuccessful) with(response) {
            if (this.code() == 200) {
                Log.v("statusUser", "succ")
                val responseUser = repository.getUser(user).await()
                if(responseUser.isSuccessful) with(responseUser){
                    if(this.code() == 200){
                        callback(this.body()!!)
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

    fun getPreguntas() = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.getPreguntas().await()
        if(response.isSuccessful){
            when(response.code()){
                200->preguntasList.postValue(response.body()?.toMutableList()?:arrayListOf(Examen("¿¿Wut", "ya")))
            }
        }else{
            //Toast.makeText(app, "Ocurrio un error", Toast.LENGTH_LONG).show()
        }
    }

}

