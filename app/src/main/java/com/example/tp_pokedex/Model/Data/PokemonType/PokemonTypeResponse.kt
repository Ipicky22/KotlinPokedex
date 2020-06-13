package com.example.tp_pokedex.Model.Data.PokemonType

import com.squareup.moshi.Json

data class PokemonTypeResponse (
    @Json(name = "name")
    val name: String
)