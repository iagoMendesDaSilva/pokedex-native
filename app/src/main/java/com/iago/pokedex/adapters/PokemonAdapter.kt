package com.iago.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iago.pokedex.databinding.CardPokemonBinding
import com.iago.pokedex.models.PokemonModel
import com.iago.pokedex.services.PokemonListener
import com.iago.pokedex.viewHolder.PokemonViewHolder

class PokemonAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mPokemonList: List<PokemonModel> = arrayListOf()
    private lateinit var mListener: PokemonListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val item = CardPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as PokemonViewHolder
        itemViewHolder.bind(mPokemonList[position])
    }

    override fun getItemCount(): Int {
        return mPokemonList.size
    }

    fun updateList(list: List<PokemonModel>) {
        mPokemonList = list
    }

    fun attachListener(listener: PokemonListener) {
        mListener = listener
    }
}