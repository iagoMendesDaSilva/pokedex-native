package com.iago.pokedex.services

import com.iago.pokedex.models.PokemonModel
import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon.json")
    fun getPokemons(): Call<List<PokemonModel>>
}