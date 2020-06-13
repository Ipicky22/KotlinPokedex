package com.example.tp_pokedex.Model.API

import com.example.tp_pokedex.Model.Data.PokemonColor.PokemonColorsResponse
import com.example.tp_pokedex.Model.Data.PokemonDetail.PokemonDetailResponse
import com.example.tp_pokedex.Model.Data.PokemonResponse
import retrofit2.Response
import retrofit2.http.*

interface IPokemonWebService {

    @GET("pokemon")
    suspend fun getPokemonWithPagination(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int = 0
    ): Response<PokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: String): Response<PokemonDetailResponse>

    @GET("pokemon-species/{id}")
    suspend fun getPokemonColor(@Path("id") id: String): Response<PokemonColorsResponse>

}

