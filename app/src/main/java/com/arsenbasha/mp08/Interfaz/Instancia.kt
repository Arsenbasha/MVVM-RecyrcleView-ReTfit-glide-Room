package com.arsenbasha.mp08.Interfaz

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Instancia {
    companion object {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val Base_uRL = "http://www.xtec.cat/~jmendez1/forteco/"
        fun getinstancia(): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl(Base_uRL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            return retrofit
        }
    }
}