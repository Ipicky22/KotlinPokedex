package com.example.tp_pokedex.Data

import com.squareup.moshi.Json

data class TypeResponse (
    @Json(name = "name")
    val name: String
)