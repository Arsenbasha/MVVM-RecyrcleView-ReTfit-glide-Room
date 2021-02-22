package com.arsenbasha.mp08.Interfaz

import com.arsenbasha.mp08.Entidad.Marcas
import retrofit2.Call
import retrofit2.http.GET

interface JsonApi {

    @GET("courses.json")
    fun getDataFromJson(): Call<List<Marcas>>
}
