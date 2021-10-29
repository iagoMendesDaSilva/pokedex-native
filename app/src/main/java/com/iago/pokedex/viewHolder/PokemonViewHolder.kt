package com.iago.pokedex.viewHolder

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iago.pokedex.models.PokemonModel
import com.iago.pokedex.services.PokemonListener
import com.iago.pokedex.services.TypesFormat
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.card_pokemon.view.*


class PokemonViewHolder(itemView: View, private val listener: PokemonListener) : RecyclerView.ViewHolder(itemView), TypesFormat {
    fun bind(pokemon: PokemonModel) {

        itemView.name.text = pokemon.name
        itemView.number.text = pokemon.id

        val types = getTypes(pokemon.typeofpokemon)
        itemView.type1.text = types[0]
        itemView.type2.text = types[1]

        val color = getColorByType(pokemon.typeofpokemon)
        val shape = itemView.backgroundCard.background as GradientDrawable
        shape.setColor(Color.parseColor(color));

        Glide.with(itemView.context).load(pokemon.imageurl).into(itemView.image);

        itemView.setOnClickListener {
            listener.onClick(pokemon, color)
        }

    }

    private fun getColorByType(typeOfPokemon: List<String>?): String {
        val type = typeOfPokemon?.getOrNull(0)
        val color = when (type?.toLowerCase()) {
            "grass", "bug" -> "#2CDAB1"
            "fire" -> "#F7786B"
            "water" -> "#58ABF6"
            "fighting" -> "#C7976F"
            "normal" -> "#DEC497"
            "electric" -> "#FFCE4B"
            "psychic" -> "#8A58F6"
            "poison" -> "#9F5BBA"
            "ghost" -> "#43348C"
            "ground", "rock" -> "#CA8179"
            "fairy" -> "#EF8EF5"
            "dark" -> "#383838"
            else -> "#58ABF6"
        }
        return color
    }

}