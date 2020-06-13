package com.example.tp_pokedex.Model.Data.PokemonColor

import com.squareup.moshi.Json

data class PokemonColorResponse (
    @Json(name = "name")
    val name: String
)