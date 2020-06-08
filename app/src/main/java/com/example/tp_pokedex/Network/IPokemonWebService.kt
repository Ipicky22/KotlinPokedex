package com.example.tp_pokedex.Network

import com.example.tp_pokedex.Data.PokemonDetailResponse
import com.example.tp_pokedex.Data.PokemonResponse
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
}
