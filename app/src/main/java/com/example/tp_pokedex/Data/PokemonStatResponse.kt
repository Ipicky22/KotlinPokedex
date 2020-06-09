package com.example.tp_pokedex.Data

import com.squareup.moshi.Json

data class PokemonStatResponse (
    @Json(name="name")
    val name: String
)
