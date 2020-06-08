package com.example.tp_pokedex.PokemonList

import com.example.tp_pokedex.Data.PokemonColorResponse
import com.example.tp_pokedex.Data.PokemonResponse
import com.example.tp_pokedex.Network.Api
import com.example.tp_pokedex.Data.PokemonDetailResponse

class PokemonRepository {

    private val pokemonWebService = Api.pokemonWebService

    suspend fun refresh(): PokemonResponse? {
        val response = pokemonWebService.getPokemon()
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getPokemonDescription(id: String): PokemonDetailResponse? {
        val response = pokemonWebService.getPokemonDetail(id)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getPokemonColor(id: String): PokemonColorResponse? {
        val response = pokemonWebService.getPokemonColor(id)
        return if (response.isSuccessful) response.body() else null
    }
}