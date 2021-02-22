package com.arsenbasha.mp08.Fragmentos

import android.app.PendingIntent.getActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.arsenbasha.mp08.Dao.UserDB
import com.arsenbasha.mp08.R
import com.arsenbasha.mp08.databinding.UserFragmentBinding
import com.bumptech.glide.Glide

class UserViewModel : ViewModel() {


    fun inflar(view: View) {
        var userDao = UserDB.getInstance(view.context).userDao()
        var binding = UserFragmentBinding.bind(view)
        val nombre: TextView = binding.nombre
        val correo: TextView = binding.correo
        val curso: TextView = binding.curso
        val perfil: ImageView = binding.perfil
        var mail = "leandroparedes0721@gmail.com"
        var user = userDao.finUser(mail)
        nombre.text=user.nombre + " " + user.appelido
        curso.text=user.curso
        correo.text=mail
        Glide.with(view.context).load(user.logo)
            .placeholder(R.drawable.progressbar).error(R.drawable.error).into(perfil)
    }
}