package com.example.teamlgl.mascota2

import Database.Mascota
import Database.MascotaData
import Database.MascotaDatabase
import Database.ioThread
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

public class ListFavoritesFragment: Fragment(), CustomItemClickListener {

    companion object {
        fun newInstance(): ListFragment = ListFragment()
        const val NOMBRE_KEY = "nombre"
        const val IMAGEN_KEY = "image"
        const val RAZA_KEY = "raza"
        const val LUGAR_KEY = "lugar"
        const val TELEFONO_KEY = "telefono"
        const val MAIL_KEY = "mail"
        const val FECHA_KEY = "fecha"
    }

    lateinit var instanceDatabase: MascotaDatabase
    lateinit var adapter: MascotaAdapter
    lateinit var recycler_listaFavoritos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun loadFavoritePets () {
        ioThread {
            val mascotasFavoritas = instanceDatabase.mascotaDao().loadFavoritePets()
            activity?.runOnUiThread {
                adapter = MascotaAdapter(mascotasFavoritas, this)
                recycler_listaFavoritos.adapter = adapter
                adapter.setMascota(mascotasFavoritas)
                recycler_listaFavoritos.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)

        val layoutManager = LinearLayoutManager(activity)
        recycler_listaFavoritos = rootView.findViewById(R.id.recycler_lista) as RecyclerView
        recycler_listaFavoritos.layoutManager = layoutManager

        instanceDatabase = MascotaDatabase.getInstance(activity!!)
        ioThread {
            val checkFavoritePets = instanceDatabase.mascotaDao().loadFavoritePets()
            if (checkFavoritePets.isEmpty()) {
                Toast.makeText(context, "No hay mascotas favoritas", Toast.LENGTH_SHORT).show()
            }
            else {
                loadFavoritePets()
            }
        }
        return rootView
    }

    override fun onCustomItemClick(mascota: Mascota) {
    }
}