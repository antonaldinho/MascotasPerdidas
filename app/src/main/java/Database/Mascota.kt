package Database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.os.Parcel
import android.os.Parcelable
import java.util.*

@Entity(tableName = "Mascota")
@TypeConverters(Converters::class)
data class Mascota (@ColumnInfo(name = "nombre") var nombre: String,
                    @ColumnInfo(name = "raza") var raza: String,
                    @ColumnInfo(name = "lugar") var lugar: String,
                    @ColumnInfo(name = "telefonoContacto") var telefonoContacto: String,
                    @ColumnInfo(name = "mailContacto") var mailContacto: String,
                    @ColumnInfo(name = "imagenMascota") var imagenMascota: ByteArray,
                    @ColumnInfo(name = "fechaRegistro") var fechaRegistro: Date?,
                    @ColumnInfo(name = "favorito") var favorito: Int){
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0
}