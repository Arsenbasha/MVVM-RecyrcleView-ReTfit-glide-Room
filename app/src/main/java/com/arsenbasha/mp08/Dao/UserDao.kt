package com.arsenbasha.mp08.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arsenbasha.mp08.Entidad.Marcas
import com.arsenbasha.mp08.Entidad.User

@Dao
interface UserDao {
    @Query("SELECT * FROM  User WHERE correo=:correo")
    fun finUser(correo:String):User
    @Update
    fun update(user: User)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg users: User)

}