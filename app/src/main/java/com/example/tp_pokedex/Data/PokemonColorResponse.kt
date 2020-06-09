package com.example.tp_pokedex.Data

import com.squareup.moshi.Json

data class PokemonColorResponse (
    @Json(name = "color")
    val color: ColorResponse
)