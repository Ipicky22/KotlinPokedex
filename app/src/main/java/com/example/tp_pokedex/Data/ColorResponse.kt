package com.example.tp_pokedex.Data

import com.squareup.moshi.Json

data class ColorResponse (
    @Json(name = "name")
    val name: String
)