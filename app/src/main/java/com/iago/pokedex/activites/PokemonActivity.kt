package com.iago.pokedex.activites

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.iago.pokedex.R
import com.iago.pokedex.constants.PokemonConstants
import com.iago.pokedex.models.PokemonModel
import com.iago.pokedex.services.TypesFormat
import kotlinx.android.synthetic.main.activity_pokemon.*


class PokemonActivity : AppCompatActivity(), TypesFormat {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        configActivity()
    }

    fun configActivity(){
        if (intent.extras != null){
            val pokemon = intent.extras!!.getSerializable(PokemonConstants.KEY_POKEMON) as PokemonModel
            val color = intent.extras!!.getSerializable(PokemonConstants.KEY_COLOR_POKEMON) as String

            name.text = pokemon.name
            number.text=pokemon.id
            backgroundPokemon.setBackgroundColor(Color.parseColor(color));
            description.text = pokemon.xdescription

            Glide.with(applicationContext).load(pokemon.imageurl).into(image);

            val types = getTypes(pokemon.typeofpokemon)
            type1.text = types[0]
            type2.text = types[1]
        }
    }
}