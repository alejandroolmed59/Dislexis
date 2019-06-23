package com.dislexisapp.dislexis.database.entities.DTO

import com.squareup.moshi.Json

data class userAuthorization(
   @field: Json(name="username")
   val username: String,

   @field: Json(name="nombreCompleto")
   val nombreCompleto: String?,

   @field: Json(name="email")
   val email: String?,

   @field: Json(name="password")
   val password: String,

   @field: Json(name="isPaciente")
   val isPaciente: String?,

   @field: Json(name="medico")
   val medico: String?
)