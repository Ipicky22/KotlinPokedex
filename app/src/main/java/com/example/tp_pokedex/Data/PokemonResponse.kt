package com.example.tp_pokedex.Data

import com.squareup.moshi.Json

data class PokemonResponse (
    @Json(name = "results")
    val results: List<PokemonListResponse>
)
