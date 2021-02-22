package com.arsenbasha.mp08.Entidad

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Marcas(
    @PrimaryKey
    val numero_seminari: Int,
    val titol: String,
    val empresa_organitzadora: String,
    val logo: String
):Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }
}
