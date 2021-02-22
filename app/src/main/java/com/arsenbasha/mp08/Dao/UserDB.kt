package com.arsenbasha.mp08.Dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arsenbasha.mp08.Entidad.User

@Database(entities = [User::class], version = 1,exportSchema = false)
abstract class UserDB : RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserDB? = null

        fun getInstance(context: Context): UserDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, UserDB::class.java, "User"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }
    }
}