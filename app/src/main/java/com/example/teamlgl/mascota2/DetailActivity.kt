package com.example.teamlgl.mascota2

import Database.Converters
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extra = intent.extras?: return

        text_nombreDetail.text = extra.getString(ListFragment.NOMBRE_KEY)
        text_mailDetail.text = extra.getString(ListFragment.MAIL_KEY)
        text_razaDetail.text = extra.getString(ListFragment.RAZA_KEY)
        text_lugarDetail.text = extra.getString(ListFragment.LUGAR_KEY)
        text_telefonoDetail.text = extra.getString(ListFragment.TELEFONO_KEY)
        image_detailMascota.setImageBitmap(Converters.toBitmap(extra.getByteArray(ListFragment.IMAGEN_KEY)!!))
        text_FavDetail.text = extra.getInt(ListFragment.BOOL_KEY).toString()
    }
}
