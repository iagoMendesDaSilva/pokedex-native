package com.iago.pokedex.services

import com.iago.pokedex.models.PokemonModel

interface PokemonListener {
    fun onClick(pokemon: PokemonModel?)
}