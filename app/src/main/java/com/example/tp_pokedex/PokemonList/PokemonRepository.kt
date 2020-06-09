package com.example.tp_pokedex.PokemonList

import com.example.tp_pokedex.Data.PokemonColorResponse
import com.example.tp_pokedex.Network.Api
import com.example.tp_pokedex.Data.PokemonDetailResponse
import com.example.tp_pokedex.Data.PokemonListResponse
import com.example.tp_pokedex.Data.PokemonResponse

class PokemonRepository {

    private val pokemonWebService = Api.pokemonWebService

    suspend fun getPokemonWithPagination(limit: Int, offset: Int): PokemonResponse? {
        val response = pokemonWebService.getPokemonWithPagination(limit, offset)
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