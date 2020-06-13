package com.example.tp_pokedex.Model.Data

import com.squareup.moshi.Json

data class PokemonTypesResponse (

    @Json(name = "slot")
    val slot: Int,
    @Json(name = "type")
    val type: TypeResponse
)