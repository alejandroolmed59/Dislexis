package com.example.olmedo.dislexis.Retrofit


import com.example.olmedo.dislexis.Entities.DTO.userAuthorization
import com.example.olmedo.dislexis.Entities.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

const val GITHUB_BASE_URL = "https://dislexisapi.herokuapp.com/"
interface UserService{

    @POST("/register/")
    fun registerUser(@Body userAuthorization: userAuthorization): Deferred<Response<User>>

    @POST("/login/")
    fun loginUser(@Body userAuthorization: userAuthorization):Deferred<Response<User>>

    companion object {

        var INSTANCE: UserService?= null

        fun getUserService():UserService {
            return if(INSTANCE!=null){
                INSTANCE!!
            }else {
                INSTANCE = Retrofit
                    .Builder()
                    .baseUrl(GITHUB_BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build().create(UserService::class.java)
                return INSTANCE!!
            }
        }
    }
}