package com.dislexisapp.dislexis.database.repositories

import androidx.annotation.WorkerThread
import com.dislexisapp.dislexis.database.daos.UserDAO
import com.dislexisapp.dislexis.database.entities.DTO.userAuthorization
import com.dislexisapp.dislexis.database.entities.User
import com.dislexisapp.dislexis.network.Desafio
import com.dislexisapp.dislexis.network.Examen
import com.dislexisapp.dislexis.network.UserRetro
import com.dislexisapp.dislexis.network.UserService

import kotlinx.coroutines.Deferred
import retrofit2.Response

class Repository (private val userDao: UserDAO, private val userService: UserService){
    @WorkerThread
    suspend fun insert(user: User){
        userDao.insert(user)
    }
    fun registerUser(userAuthorization: userAuthorization):Deferred<Response<User>>{
       return userService.registerUser(userAuthorization)
    }
    fun loginUser(userAuthorization: userAuthorization):Deferred<Response<User>> {
        return userService.loginUser(userAuthorization)
    }
    fun getUser(username: String):Deferred<Response<UserRetro>> {
        return userService.getUser(username)
    }
    fun getPreguntas():Deferred<Response<List<Examen>>> {
        return userService.getPreguntas()
    }
    fun subirExamen(username: String, contador: Int) : Deferred<Response<Examen>>{
        return userService.subirExamen(username, contador)
    }
    fun getDesafios(): Deferred<Response<List<Desafio>>>{
        return userService.getDesafios()
    }
}