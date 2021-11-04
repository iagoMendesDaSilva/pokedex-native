package com.iago.pokedex.viewHolder

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iago.pokedex.databinding.CardPokemonBinding
import com.iago.pokedex.models.PokemonModel
import com.iago.pokedex.services.FormatPokemon
import com.iago.pokedex.services.PokemonListener

class PokemonViewHolder(var item: CardPokemonBinding, private val listener: PokemonListener) :
    RecyclerView.ViewHolder(item.root), FormatPokemon {

    fun bind(pokemon: PokemonModel) {
        item.name.text = pokemon.name
        item.number.text = pokemon.id

        val types = getTypes(pokemon.typeofpokemon)
        item.type1.text = types[0]
        item.type2.text = types[1]

        val color = getColorByType(item.root.context, pokemon.typeofpokemon)
        item.backgroundCard.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        Glide.with(item.root.context).load(pokemon.imageurl).into(item.image);

        item.root.setOnClickListener {
            listener.onClick(pokemon)
        }

    }

}