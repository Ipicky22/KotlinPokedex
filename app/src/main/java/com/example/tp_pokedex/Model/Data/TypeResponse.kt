package com.example.tp_pokedex.Model.Data

import com.squareup.moshi.Json

data class TypeResponse (
    @Json(name = "name")
    val name: String
)