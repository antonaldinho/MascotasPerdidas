package com.example.teamlgl.mascota2

import Database.Converters
import Database.Mascota
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row.view.*

class MascotaAdapter (val mascotas: List<Mascota>,
                      val listener: CustomItemClickListener): RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> (){

    lateinit var mascota: Mascota
    var numberOfItems = mascotas.size
    private var mascotas2: List<Mascota>? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MascotaAdapter.MascotaViewHolder {
        val mascotaViewHolder: MascotaAdapter.MascotaViewHolder
        val rowView = LayoutInflater.from(p0.context).inflate(R.layout.row, p0, false)

        mascotaViewHolder = MascotaViewHolder(rowView)
        return mascotaViewHolder
    }

    override fun getItemCount(): Int {
        return numberOfItems
    }

    override fun onBindViewHolder(p0: MascotaAdapter.MascotaViewHolder, p1: Int) {
        p0.bind(p1)
    }

    inner class MascotaViewHolder (itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        fun bind (index: Int) {
            mascota = mascotas[index]

            itemView.text_nombre.text = mascota.nombre
            itemView.text_raza.text = mascota.raza
            itemView.text_lugar.text = mascota.lugar
            itemView.image_mascota.setImageBitmap(Converters.toBitmap(mascota.imagenMascota))
        }

        override fun onClick(p0: View?) {
            val pet = mascotas[adapterPosition]
            listener.onCustomItemClick(pet)
        }
    }

    fun setMascota(mascotas: List<Mascota>) {
        this.mascotas2 = mascotas
        numberOfItems = mascotas.size
    }
}