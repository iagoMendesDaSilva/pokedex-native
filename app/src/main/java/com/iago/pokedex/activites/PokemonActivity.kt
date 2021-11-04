package com.iago.pokedex.activites

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.iago.pokedex.R
import com.iago.pokedex.constants.PokemonConstants
import com.iago.pokedex.databinding.ActivityPokemonBinding
import com.iago.pokedex.models.PokemonModel
import com.iago.pokedex.services.FormatPokemon

class PokemonActivity : AppCompatActivity(), FormatPokemon {

    private lateinit var binding: ActivityPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configActivity()
    }

    fun configActivity() {
        if (intent.extras != null) {
            val pokemon = intent.extras!!.getSerializable(PokemonConstants.KEY_POKEMON) as PokemonModel
            binding.name.text = pokemon.name
            binding.number.text = pokemon.id
            binding.description.text = pokemon.xdescription ?: getString(R.string.no_description)

            val color = getColorByType(applicationContext, pokemon.typeofpokemon)
            binding.backgroundPokemon.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            Glide.with(applicationContext).load(pokemon.imageurl).into(binding.image);

            val types = getTypes(pokemon.typeofpokemon)
            binding.type1.text = types[0]
            binding.type2.text = types[1]
        }
    }
}