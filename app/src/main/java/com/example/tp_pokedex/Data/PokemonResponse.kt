package com.example.tp_pokedex.Data

import com.squareup.moshi.Json

data class PokemonResponse (
    @Json(name = "count")
    val count : Int,
    @Json(name = "next")
    val next: String,
    @Json(name = "previous")
    val previous: String,
    @Json(name = "results")
    val results: List<PokemonListResponse>
)