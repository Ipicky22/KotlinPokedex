package com.example.tp_pokedex.Model.Data

import com.example.tp_pokedex.Model.Data.PokemonList.PokemonListResponse
import com.squareup.moshi.Json

data class PokemonResponse (
    @Json(name = "count")
    val count: Int,
    @Json(name = "results")
    val results: List<PokemonListResponse>
)
