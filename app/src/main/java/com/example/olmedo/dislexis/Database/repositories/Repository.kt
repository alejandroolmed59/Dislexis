package com.example.olmedo.dislexis.Database.repositories

import androidx.annotation.WorkerThread
import com.example.olmedo.dislexis.Database.daos.UserDAO
import com.example.olmedo.dislexis.Database.entities.DTO.userAuthorization
import com.example.olmedo.dislexis.Database.entities.User
import com.example.olmedo.dislexis.Database.entities.UserLogged
import com.example.olmedo.dislexis.Network.Examen
import com.example.olmedo.dislexis.Network.UserRetro
import com.example.olmedo.dislexis.Network.UserService

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

    fun getPreguntas():Deferred<Response<Examen>> {
        return userService.getPreguntas()
    }
}