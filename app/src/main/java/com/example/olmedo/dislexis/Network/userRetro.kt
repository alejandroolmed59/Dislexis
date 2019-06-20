package com.example.olmedo.dislexis.Network



import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class UserRetro(

    @field: Json(name="_id")
    val _id: String?="N/A",


    @field: Json(name="medicoReferencia")
    val medicoReferencia:String?="N/A",

    @field: Json(name="username")
    val username: String?="N/A",

    @field: Json(name="email")
    val email:String?="N/A",

    @field: Json(name="isPaciente")
    val isPaciente:String?="N/A",

    @field: Json(name="examenes")
    var examenes: List<Examen>?= emptyList(),

    @field: Json(name="pacientes")
    var pacientes: List<Paciente>? = emptyList()

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

data class Examen (
    @field: Json(name="notoquenesto")
    val pregunta: String?,

    @field: Json(name="respuesta")
    val respuesta:String?
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(pregunta)
        parcel.writeString(respuesta)
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
    val _id: String?,

    @field: Json(name="usernamePaciente")
    val usernamePaciente:String?
)
