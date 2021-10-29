package com.iago.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iago.pokedex.R
import com.iago.pokedex.models.PokemonModel
import com.iago.pokedex.services.PokemonListener
import com.iago.pokedex.viewHolder.PokemonViewHolder

class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>() {

    private var mPokemonList: List<PokemonModel> = arrayListOf()
    private lateinit var mListener : PokemonListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon, parent, false)
        return PokemonViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(mPokemonList[position])
    }

    override fun getItemCount(): Int {
        return mPokemonList.count()
    }

    fun updateList(list: List<PokemonModel>){
        mPokemonList = list
    }

    fun attachListener(listener: PokemonListener){
        mListener = listener
    }
}