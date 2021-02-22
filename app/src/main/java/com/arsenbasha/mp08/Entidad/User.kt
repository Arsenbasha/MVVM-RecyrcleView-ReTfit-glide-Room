package com.arsenbasha.mp08.Entidad

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val correo: String,
    val nombre: String,
    val appelido: String,
    val curso:String,
    val logo: String
)