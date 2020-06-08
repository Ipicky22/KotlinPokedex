package com.example.tp_pokedex.Data

import com.squareup.moshi.Json

data class PokemonDetailResponse (
    @Json(name = "name")
    val name: String,
    @Json(name = "height")
    val height: String,
    @Json(name = "weight")
    val weight: String
)