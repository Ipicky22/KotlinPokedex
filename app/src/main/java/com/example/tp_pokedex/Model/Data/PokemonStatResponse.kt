package com.example.tp_pokedex.Model.Data

import com.squareup.moshi.Json

data class PokemonStatResponse (
    @Json(name="name")
    val name: String
)
