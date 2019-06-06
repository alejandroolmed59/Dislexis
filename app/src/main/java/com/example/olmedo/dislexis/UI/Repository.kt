package com.example.olmedo.dislexis.UI

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.olmedo.dislexis.Database.UserDAO
import com.example.olmedo.dislexis.Entities.DTO.userAuthorization
import com.example.olmedo.dislexis.Entities.User
import com.example.olmedo.dislexis.Retrofit.UserService

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class Repository (private val userDao:UserDAO, private val userService: UserService){


    @WorkerThread
    suspend fun insert(user:User){
        userDao.insert(user)
    }


    fun registerUser(userAuthorization: userAuthorization):Deferred<Response<User>>{
       return userService.registerUser(userAuthorization)
    }

    fun loginUser(userAuthorization: userAuthorization):Deferred<Response<User>> {
        return userService.loginUser(userAuthorization)
    }
}