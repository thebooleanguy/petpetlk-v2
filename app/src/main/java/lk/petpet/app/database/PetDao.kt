package lk.petpet.app.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PetDao {
    @Query("SELECT * FROM pet")
    fun getAllPets(): List<Pet>

    @Query("SELECT * FROM pet WHERE petId = :id LIMIT 1")
    fun getPetById(id: Int): Pet

    @Insert
    fun insertPet(vararg pets: Pet)

    @Update
    fun updatePet(pet: Pet)

    @Delete
    fun deletePet(pet: Pet)
}
