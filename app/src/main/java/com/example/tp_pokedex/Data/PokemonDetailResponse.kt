package com.example.tp_pokedex.Data

import com.squareup.moshi.Json

data class PokemonDetailResponse (
    @Json(name = "name")
    val name: String
)