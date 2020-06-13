package com.example.tp_pokedex.Model.Data

import com.squareup.moshi.Json

data class ColorResponse (
    @Json(name = "name")
    val name: String
)