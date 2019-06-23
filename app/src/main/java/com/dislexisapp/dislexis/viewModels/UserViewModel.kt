package com.dislexisapp.dislexis.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dislexisapp.dislexis.database.RoomDB
import com.dislexisapp.dislexis.database.repositories.Repository
import com.dislexisapp.dislexis.database.entities.DTO.userAuthorization
import com.dislexisapp.dislexis.database.entities.User
import com.dislexisapp.dislexis.network.Desafio
import com.dislexisapp.dislexis.network.Examen
import com.dislexisapp.dislexis.network.UserRetro
import com.dislexisapp.dislexis.network.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val app: Application) : AndroidViewModel(app) {
    private val repository: Repository
    var code = MutableLiveData<Int>()
    var userLD = MutableLiveData<UserRetro>()

    init {
        val userDao = RoomDB.getInstance(app).userDao()
        val userService = UserService.getUserService()
        repository = Repository(userDao, userService)
    }
    val preguntasList = MutableLiveData<MutableList<Examen>>()
    val desafiosList = MutableLiveData<MutableList<Desafio>>()

    private suspend fun insert(user: User) = repository.insert(user)


    fun registerUser(userAuthorization: userAuthorization, callback: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.v("LLEGO", "1")
            val response = repository.registerUser(userAuthorization).await()

            if (response.isSuccessful) with(response) {
                if (this.code() == 200) {
                    code.postValue(200)
                    Log.v("registerStatus", "succes")
//                Log.v("registerStatus", this.body()?.username)
                    callback(true)
                } else {
                    callback(false)
                    Log.v("registerStatus", "fail1")
                }
            } else {
                callback(false)
                code.postValue(500)
                Log.v("registerStatus", "fail2")
                // Toast.makeText(app, "Error con el registro", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun loginUser(user: String, password: String, callback: (UserRetro) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.loginUser(
                userAuthorization(
                    user,
                    null,
                        null,
                    password,
                    null,
                    null
                )
            ).await()
            if (response.isSuccessful) with(response) {
                if (this.code() == 200) {
                    code.postValue(200)
                    Log.v("statusUser", "succ")
                    val responseUser = repository.getUser(user).await()
                    if (responseUser.isSuccessful) with(responseUser) {
                        if (this.code() == 200) {
                            callback(this.body()!!)
                        }
                    } else {

                    }
                } else {
                    code.postValue(600)
                    Log.v("statusUser1", "fail1")
                }
            } else {
                code.postValue(500)
                Log.v("statusUser2", "fail2")
            }
        }
    }
    fun getUser (user: String) = viewModelScope.launch(Dispatchers.IO) {
        val responseUser = repository.getUser(user).await()
        if (responseUser.isSuccessful) with(responseUser) {
            if (this.code() == 200) {
                userLD.postValue(this.body()!!)
            }
        } else {

        }
    }

    fun getPreguntas() = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.getPreguntas().await()
        if(response.isSuccessful){
            when(response.code()){
                200->preguntasList.postValue(response.body()?.toMutableList()?:arrayListOf(Examen("¿¿Wut", "ya", "no", "no")))
            }
        }else{
            //Toast.makeText(app, "Ocurrio un error", Toast.LENGTH_LONG).show()
        }
    }
    fun subirExamen(username: String, contador: Int) = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.subirExamen(username, contador).await()
        if(response.isSuccessful){
            when(response.code()){
                200-> Log.v("subida", "Respuesta subida")
            }
        }
    }
    fun getDesafios() = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.getDesafios().await()
        if(response.isSuccessful){
            when(response.code()){
                200->desafiosList.postValue(response.body()?.toMutableList()?:arrayListOf(Desafio("", "", "", "", "", "")))
            }
        }else{
            //Toast.makeText(app, "Ocurrio un error", Toast.LENGTH_LONG).show()
        }
    }
}

