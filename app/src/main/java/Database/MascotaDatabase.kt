package Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context

@Database(entities = arrayOf(Mascota::class),version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MascotaDatabase: RoomDatabase() {
    abstract fun mascotaDao(): MascotaDao

    companion object {
        private val DATABASE_NAME = "MascotaDB.db"
        private var dbInstance: MascotaDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MascotaDatabase {
            if (dbInstance == null) {
                dbInstance = buildDatabase(context)
            }
            return dbInstance!!
        }

        private fun buildDatabase(context: Context): MascotaDatabase {
            return Room.databaseBuilder(context,
                    MascotaDatabase::class.java,
                    DATABASE_NAME).build()
        }
    }
}