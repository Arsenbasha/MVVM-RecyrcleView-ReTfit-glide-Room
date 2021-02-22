package com.arsenbasha.mp08.Dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.arsenbasha.mp08.Entidad.Marcas


@Dao
interface MarcasDao {
    @Query("SELECT * FROM  Marcas")
     fun getAll(): List<Marcas>
    @Query("SELECT * FROM  Marcas")
    fun findAll():LiveData<List<Marcas>>

    @Update
     fun update(marca: Marcas)

    @Insert(onConflict= OnConflictStrategy.REPLACE)
     fun insertar(marcas: List<Marcas>)
}