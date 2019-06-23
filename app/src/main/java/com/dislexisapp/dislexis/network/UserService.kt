package com.dislexisapp.dislexis.network


import com.dislexisapp.dislexis.database.entities.DTO.userAuthorization
import com.dislexisapp.dislexis.database.entities.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

const val GITHUB_BASE_URL = "https://dislexisapi.herokuapp.com/"
interface UserService{

    @POST("/register/")
    fun registerUser(@Body userAuthorization: userAuthorization): Deferred<Response<User>>

    @POST("/login/")
    fun loginUser(@Body userAuthorization: userAuthorization):Deferred<Response<User>>

    @GET("getOneUser/{username}/")
    fun getUser(@Path("username") username: String ):Deferred<Response<UserRetro>>

    @GET("/pregunta/")
    fun getPreguntas():Deferred<Response<List<Examen>>>

    @POST("/getOneUser/{username}/{contador}")
    fun subirExamen(@Path("username") username: String, @Path("contador") contador: Int) :Deferred<Response<Examen>>

    @GET("/desafio/")
    fun getDesafios():Deferred<Response<List<Desafio>>>

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