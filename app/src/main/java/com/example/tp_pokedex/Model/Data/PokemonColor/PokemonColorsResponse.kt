package com.example.tp_pokedex.Model.Data.PokemonColor

import com.squareup.moshi.Json

data class PokemonColorsResponse (
    @Json(name = "color")
    val color: PokemonColorResponse
)