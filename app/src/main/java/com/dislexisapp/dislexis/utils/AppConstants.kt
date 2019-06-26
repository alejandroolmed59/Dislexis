package com.dislexisapp.dislexis.utils

import com.dislexisapp.dislexis.network.ExamenRetro
import com.dislexisapp.dislexis.network.Paciente
import com.dislexisapp.dislexis.network.UserRetro

object AppConstants {
    var user: UserRetro? = null
    var rolOriginal : String = "PacienteDef"
    val limiteDeDesafios: Int = 2  //EN REALIDAD ES DE LAS PREGUNTAS DE LA ACTIVIDAD DIAGNOSTICO NOOO DE TEST
    val LOGGED_IN_PREF = "logged_in_status"
    val USER_PREF = "user_pref"
    val PASSWORD_PREF = "password_pref"
    /*var ID_PREF = ""
    var MEDICO_PREF = ""
    var NOMBRE_PREF = ""
    var EMAIL_PREF = ""
    var EXAMENES_STRING_PREF = ""
    var PACIENTES_STRING_PREF = ""*/
}