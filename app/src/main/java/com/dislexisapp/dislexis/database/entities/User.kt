package com.dislexisapp.dislexis.database.entities

//import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "user")
data class User(

    @field: Json(name="_id")
    val _id: String?,

    @PrimaryKey
    @field: Json(name="username")
    val username: String,

    @field: Json(name="email")
    val email:String?,

    @field: Json(name="medicoReferencia")
    val medicoReferencia:String?
)


data class UserLogged(
    @field: Json(name="username")
    val username: String

)