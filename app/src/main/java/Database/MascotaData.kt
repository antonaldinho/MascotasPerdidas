package Database

import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.teamlgl.mascota2.R

@TypeConverters(Converters::class)
class MascotaData (val context: Context){
    val mascotaList: ArrayList<Mascota> = ArrayList()

    init {
        dataList()
    }

    fun getBitmap(imageId: Int): Bitmap = BitmapFactory.decodeResource(context.resources, imageId)

    fun dataList() {
        mascotaList.add(Mascota(
                "Firulais",
                "Chihuahua",
                "Monterrey",
                "8119758199",
                "antonio.9714@gmail.com",
                Converters.toByteArray(getBitmap(R.drawable.firulais)),
                Converters.toDate("2018/27/10")!!,
                0
        ))

        mascotaList.add(Mascota(
                "Perrin",
                "Shiba",
                "Tokyo",
                "8191026745",
                "carlosmiranda@gmail.com",
                Converters.toByteArray(getBitmap(R.drawable.shiba)),
                Converters.toDate("2018/27/10")!!,
                1
        ))

        mascotaList.add(Mascota(
                "Regino",
                "Labrador",
                "Monterrey",
                "8191026745",
                "carlosmiranda@gmail.com",
                Converters.toByteArray(getBitmap(R.drawable.labrador)),
                Converters.toDate("2018/27/10")!!,
                0
        ))
    }
}