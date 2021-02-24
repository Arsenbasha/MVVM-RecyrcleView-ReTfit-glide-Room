package com.arsenbasha.mp08.Fragmentos

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isGone
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arsenbasha.mp08.Dao.MarcasDao
import com.arsenbasha.mp08.Dao.MarcasDaoDB
import com.arsenbasha.mp08.R
import com.arsenbasha.mp08.Entidad.Marcas
import com.arsenbasha.mp08.Interfaz.Instancia
import com.arsenbasha.mp08.Interfaz.JsonApi
import com.arsenbasha.mp08.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel() : ViewModel() {
    lateinit var marcasDao: MarcasDao
    lateinit var marcas: List<Marcas>
    fun callGetMarcas(view: View, context: Context?, refrezcar: Boolean) {
        val interfaz: JsonApi = Instancia.getinstancia().create(JsonApi::class.java)
        val resultados = interfaz.getDataFromJson()
        marcasDao = MarcasDaoDB.getInstance(context!!).marcasDao()
        val conexion = view.findViewById<ImageView>(com.arsenbasha.mp08.R.id.conexion)
        val loadingView = view.findViewById<ProgressBar>(com.arsenbasha.mp08.R.id.progreso)
        val volver = view.findViewById<Button>(R.id.volver)
        val refresh = view.findViewById<SwipeRefreshLayout>(R.id.refescar)
        if (marcasDao.getAll().isEmpty() || refrezcar) {
            resultados.enqueue(object : Callback<List<Marcas>> {
                override fun onFailure(call: Call<List<Marcas>>, t: Throwable) {
                    loadingView.isGone = true
                    conexion.isGone = false
                    volver.isGone = false
                    Log.d("INFO", "${t.message}")
                    Toast.makeText(
                        context,
                        "No se han podido cargar los datos ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    volver.setOnClickListener {
                        callGetMarcas(view, context, false)
                    }
                    if (!marcasDao.getAll().isEmpty()) ocultar(loadingView, conexion, volver)
                }

                override fun onResponse(
                    call: Call<List<Marcas>>,
                    response: Response<List<Marcas>>
                ) {
                    conexion.isGone = true
                    loadingView.isGone = true
                    volver.isGone = true
                    Log.d("INFO", "marcas cogidas del ${response.raw()}")
                    marcas = response.body()!!
                    Log.d("INFO", "Insertando Marca en b")
                    marcasDao.insertar(marcas)

                }
            })
        } else ocultar(loadingView, conexion, volver)
    }

    fun findAllMarcas(): LiveData<List<Marcas>> {
        return marcasDao.findAll()
    }

    private fun ocultar(
        loadingView: ProgressBar,
        conexion: ImageView,
        volver: Button
    ) {
        loadingView.isGone = true
        conexion.isGone = true
        volver.isGone = true
    }

/*
    lateinit var _getmarcas: MutableLiveData<List<Marcas>>

    init {
        _getmarcas = MutableLiveData()
        getAllMarcas()
    }

    fun getAllMarcas() {

        val userDao = MarcasDaoDB.getInstance().marcasDao()
        val list = userDao.getAll()
        _getmarcas.postValue(list)
    }

    fun inserData( datos: MutableLiveData<List<Marcas>>) {
        _context = context!!
        val bd = MarcasDaoDB.getInstance(context).marcasDao().insertar(datos)
    }*/
}