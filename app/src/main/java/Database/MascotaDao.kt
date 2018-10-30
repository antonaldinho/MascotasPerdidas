package Database

import android.arch.persistence.room.*
@Dao
interface MascotaDao {
    @Query("SELECT * FROM Mascota")
    fun loadAllPets(): List<Mascota>

    @Insert
    fun insertPet(mascota: Mascota)

    @Insert
    fun insertPetList(mascotas: List<Mascota>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePet(mascota: Mascota)

    @Delete
    fun deletePet(mascota: Mascota)

    @Query("SELECT * FROM Mascota WHERE _id = :id")
    fun loadPetById(id: Int): Mascota

    @Query("UPDATE Mascota SET favorito = 1 WHERE _id = :id")
    fun markPet(id: Int)

    @Query("UPDATE Mascota SET favorito = 0 WHERE _id = :id")
    fun unmarkPet(id: Int)

    @Query("SELECT favorito from Mascota WHERE _id = :id")
    fun checkFavorite(id: Int): Int

    @Query("SELECT * FROM Mascota WHERE favorito = 1")
    fun loadFavoritePets(): List<Mascota>
}