package lk.petpet.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lk.petpet.app.database.Pet
import lk.petpet.app.database.PetDatabase
import lk.petpet.app.database.PetAdapter
import lk.petpet.app.database.PetDao

class PetListActivity : AppCompatActivity() {

    private lateinit var petListRecyclerView: RecyclerView
    private lateinit var petAdapter: PetAdapter
    private lateinit var petDao: PetDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_database_test)

        petListRecyclerView = findViewById(R.id.petListRecyclerView)
        petAdapter = PetAdapter(listOf()) // Initial empty list
        petListRecyclerView.layoutManager = LinearLayoutManager(this)
        petListRecyclerView.adapter = petAdapter

        // Initialize Room database
        val db = PetDatabase.getDatabase(applicationContext)
        petDao = db.petDao()

        // Insert Pet Button Click Listener
        val insertButton = findViewById<Button>(R.id.insert_button)
        insertButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.name_input).text.toString()
            val age = findViewById<EditText>(R.id.age_input).text.toString().toIntOrNull() ?: 0
            val breed = findViewById<EditText>(R.id.breed_input).text.toString()

            val newPet = Pet(name = name, age = age, breed = breed)

            CoroutineScope(Dispatchers.IO).launch {
                petDao.insertPet(newPet)
                loadPets() // Reload the list after insertion
            }
        }

        // Load pets from the database initially
        loadPets()
    }

    private fun loadPets() {
        // Load pets from the database and update the RecyclerView
        CoroutineScope(Dispatchers.IO).launch {
            val pets = petDao.getAllPets()
            CoroutineScope(Dispatchers.Main).launch {
                petAdapter.updatePets(pets)
            }
        }
    }
}
