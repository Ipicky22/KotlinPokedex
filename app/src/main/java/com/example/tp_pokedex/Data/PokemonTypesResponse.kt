package com.example.tp_pokedex.Data

import com.squareup.moshi.Json

data class PokemonTypesResponse (

    @Json(name = "slot")
    val slot: String,
    @Json(name = "type")
    val type: List<TypeResponse>
)