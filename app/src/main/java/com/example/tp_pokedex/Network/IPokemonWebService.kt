package com.example.tp_pokedex.Network

import com.example.tp_pokedex.Data.PokemonColorResponse
import com.example.tp_pokedex.Data.PokemonDetailResponse
import com.example.tp_pokedex.Data.PokemonListResponse
import com.example.tp_pokedex.Data.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IPokemonWebService {

    @GET("pokemon-species")
    suspend fun getPokemon(): Response<PokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: String): Response<PokemonDetailResponse>

    @GET("/pokemon-species/{id}")
    suspend fun getPokemonColor(@Path("id") id: String): Response<PokemonColorResponse>

}