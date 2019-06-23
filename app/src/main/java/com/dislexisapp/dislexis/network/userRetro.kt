package com.dislexisapp.dislexis.network



import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class UserRetro(

    @field: Json(name="_id")
    val _id: String?="N/A",


    @field: Json(name="medicoReferencia")
    val medicoReferencia:String="N/A",

    @field: Json(name="username")
    val username: String="N/A",

    @field: Json(name="nombreCompleto")
    val nombreCompleto: String="N/A",

    @field: Json(name="email")
    val email:String="N/A",

    @field: Json(name="isPaciente")
    val isPaciente:String="N/A",

    @field: Json(name="examenes")
    var examenes: List<ExamenRetro> = emptyList(),

    @field: Json(name="pacientes")
    var pacientes: List<Paciente> = emptyList()

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(medicoReferencia)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(isPaciente)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserRetro> {
        override fun createFromParcel(parcel: Parcel): UserRetro {
            return UserRetro(parcel)
        }

        override fun newArray(size: Int): Array<UserRetro?> {
            return arrayOfNulls(size)
        }
    }
}

data class ExamenRetro (
    @field: Json(name="_id")
    val _id: String= "N/A",

    @field: Json(name="correctas")
    val correctas:String= "N/A"
)

data class Examen (
    @field: Json(name="pregunta")
    val pregunta: String= "N/A",

    @field: Json(name="respuesta1")
    val respuesta1:String= "N/A",

    @field: Json(name="respuesta2")
    val respuesta2:String= "N/A",

    @field: Json(name="respuestaCorrecta")
    val respuestaCorrecta:String= "N/A"

):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(pregunta)
        parcel.writeString(respuesta1)
        parcel.writeString(respuesta2)
        parcel.writeString(respuestaCorrecta)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Examen> {
        override fun createFromParcel(parcel: Parcel): Examen {
            return Examen(parcel)
        }

        override fun newArray(size: Int): Array<Examen?> {
            return arrayOfNulls(size)
        }
    }
}

data class Paciente (
    @field: Json(name="_id")
    val _id: String= "N/A",

    @field: Json(name="usernamePaciente")
    val usernamePaciente:String= "N/A",

    @field: Json(name="nombreCompleto")
    val nombreCompleto:String= "N/A"
)

data class Desafio (
        @field: Json(name="img")
        val img: String = "N/A",

        @field: Json(name="respuesta1")
        val respuesta1:String = "N/A",

        @field: Json(name="respuesta2")
        val respuesta2:String = "N/A",

        @field: Json(name="respuesta3")
        val respuesta3:String = "N/A",

        @field: Json(name="respuesta4")
        val respuesta4:String = "N/A",

        @field: Json(name="respuestaCorrecta")
        val respuestaCorrecta: String = "N/A"
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(img)
        parcel.writeString(respuesta1)
        parcel.writeString(respuesta2)
        parcel.writeString(respuesta3)
        parcel.writeString(respuesta4)
        parcel.writeString(respuestaCorrecta)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Desafio> {
        override fun createFromParcel(parcel: Parcel): Desafio {
            return Desafio(parcel)
        }

        override fun newArray(size: Int): Array<Desafio?> {
            return arrayOfNulls(size)
        }
    }
}