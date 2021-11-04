package com.iago.pokedex.services

import android.content.Context
import androidx.core.content.ContextCompat
import com.iago.pokedex.R

interface FormatPokemon {
    fun getTypes(types: List<String>): ArrayList<String> {

        var correctTypes = arrayListOf<String>()

        if (types.isNotEmpty()) {
            correctTypes.add(types[0])
            if (types.size > 1) {
                correctTypes.add(types[1])
            }else{
                correctTypes.add("")
            }
        }

        return correctTypes
    }

     fun getColorByType(context: Context, typeOfPokemon: List<String>?): Int {
        val type = typeOfPokemon?.getOrNull(0)
        val color = when (type?.toLowerCase()) {
            "grass", "bug" -> R.color.grass_or_bug
            "fire" -> R.color.fire
            "water" -> R.color.water
            "fighting" -> R.color.fighting
            "normal" -> R.color.normal
            "electric" -> R.color.electric
            "psychic" -> R.color.psychic
            "poison" -> R.color.poison
            "ghost" -> R.color.ghost
            "ground", "rock" -> R.color.ground_or_rock
            "fairy" -> R.color.fairy
            "dark" -> R.color.dark
            else -> R.id.normal
        }
        return ContextCompat.getColor(context, color)
    }
}