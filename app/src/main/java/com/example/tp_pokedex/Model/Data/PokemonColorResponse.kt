package com.example.tp_pokedex.Model.Data

import com.example.tp_pokedex.Model.Data.ColorResponse
import com.squareup.moshi.Json

data class PokemonColorResponse (
    @Json(name = "color")
    val color: ColorResponse
)