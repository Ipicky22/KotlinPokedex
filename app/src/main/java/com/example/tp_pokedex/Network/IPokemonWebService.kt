package com.example.tp_pokedex.Network

import com.example.tp_pokedex.Data.PokemonDetailResponse
import com.example.tp_pokedex.Data.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IPokemonWebService {

    @GET("pokemon-species")
    suspend fun getPokemon(): Response<PokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: String): Response<PokemonDetailResponse>

    @GET("pokemon")
    suspend fun getPaginationPokemons(@Query("limit") limit: Int, @Query("offset") offset: Int = 0): Response<PokemonResponse>

}
