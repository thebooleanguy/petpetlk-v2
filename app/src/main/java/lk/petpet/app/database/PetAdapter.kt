package lk.petpet.app.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lk.petpet.app.R

class PetAdapter(private var petList: List<Pet>) : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pet, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet = petList[position]
        holder.nameTextView.text = pet.name
        holder.ageTextView.text = pet.age.toString()
        holder.breedTextView.text = pet.breed
    }

    override fun getItemCount(): Int {
        return petList.size
    }

    fun updatePets(pets: List<Pet>) {
        petList = pets
        notifyDataSetChanged()
    }

    inner class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.petName)
        val ageTextView: TextView = itemView.findViewById(R.id.petAge)
        val breedTextView: TextView = itemView.findViewById(R.id.petBreed)
    }
}
