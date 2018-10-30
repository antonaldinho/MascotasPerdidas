package com.example.teamlgl.mascota2

import Database.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import java.lang.RuntimeException

public class ListFragment: Fragment(), CustomItemClickListener {

    companion object {
        fun newInstance(): ListFragment = ListFragment()
        const val NOMBRE_KEY = "nombre"
        const val IMAGEN_KEY = "image"
        const val RAZA_KEY = "raza"
        const val LUGAR_KEY = "lugar"
        const val TELEFONO_KEY = "telefono"
        const val MAIL_KEY = "mail"
        const val FECHA_KEY = "fecha"
        const val BOOL_KEY = "key"
    }

    lateinit var instanceDatabase: MascotaDatabase
    lateinit var adapter: MascotaAdapter
    lateinit var recycler_listaNormal: RecyclerView
    //var listener: CustomItemClickListener ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun loadPets () {
        ioThread {
            val mascotas = instanceDatabase.mascotaDao().loadAllPets()
            activity?.runOnUiThread {
                adapter = MascotaAdapter(mascotas, this)
                recycler_listaNormal.adapter = adapter
                adapter.setMascota(mascotas)
                recycler_listaNormal.adapter?.notifyDataSetChanged()
            }
        }
    }

    fun insertPets (context: Context) {
        val mascotas: ArrayList<Mascota> = MascotaData(activity!!.applicationContext).mascotaList
        ioThread {
            instanceDatabase.mascotaDao().insertPetList(mascotas)
            activity?.runOnUiThread {
                adapter = MascotaAdapter(mascotas, this)
                recycler_listaNormal.adapter = adapter
                adapter.setMascota(mascotas)
                recycler_listaNormal.adapter?.notifyDataSetChanged()
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)

        val layoutManager = LinearLayoutManager(activity)
        recycler_listaNormal = rootView.findViewById(R.id.recycler_lista) as RecyclerView
        recycler_listaNormal.layoutManager = layoutManager

        instanceDatabase = MascotaDatabase.getInstance(activity!!)
        ioThread {
            val checkMascotas = instanceDatabase.mascotaDao().loadAllPets()
            if (checkMascotas.isEmpty()) {
                insertPets(activity!!)
            }
            else {
                loadPets()
            }
        }
        return rootView
    }

    override fun onCustomItemClick(mascota: Mascota) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(NOMBRE_KEY, mascota.nombre)
        intent.putExtra(RAZA_KEY, mascota.raza)
        intent.putExtra(LUGAR_KEY, mascota.lugar)
        intent.putExtra(MAIL_KEY, mascota.mailContacto)
        intent.putExtra(TELEFONO_KEY, mascota.telefonoContacto)
        val fechaString = Converters.toString(mascota.fechaRegistro)
        intent.putExtra(FECHA_KEY, fechaString)
        intent.putExtra(IMAGEN_KEY, mascota.imagenMascota)
        intent.putExtra(BOOL_KEY, mascota.favorito)
        startActivity(intent)
    }

    /*
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is CustomItemClickListener) {
            listener = context
        }
        else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    */
}