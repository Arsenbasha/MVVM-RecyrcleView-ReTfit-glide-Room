package com.arsenbasha.mp08.Adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.arsenbasha.mp08.Entidad.Marcas
import com.arsenbasha.mp08.Fragmentos.HomeFragmentDirections
import com.arsenbasha.mp08.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener


class MarcasAdapter(
    private val marcaList: List<Marcas>
) :
    RecyclerView.Adapter<MarcasAdapter.MarcaViewHolder>() {


    inner class MarcaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.titulo)
        val seminario: TextView = view.findViewById(R.id.seminario)
        val empresa: TextView = view.findViewById(R.id.empresa)
        val logo: ImageView = view.findViewById(R.id.logo)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarcaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.marca_item, parent, false)
        return MarcaViewHolder(view)
    }

    override fun getItemCount() = marcaList.size
    override fun onBindViewHolder(holder: MarcaViewHolder, position: Int) {

        Glide.with(holder.logo.context).load(marcaList[position].logo).placeholder(R.drawable.progressbar).error(R.drawable.error).into(holder.logo)
        holder.empresa.text = marcaList[position].empresa_organitzadora
        holder.titulo.text = marcaList[position].titol
        holder.seminario.text = marcaList[position].numero_seminari.toString()
        val marca: Marcas = marcaList[position]
        val action =HomeFragmentDirections.actionNavHomeToMarca(marca)

        holder.itemView.setOnClickListener{v -> Navigation.findNavController(v).navigate(action)}


    }

}