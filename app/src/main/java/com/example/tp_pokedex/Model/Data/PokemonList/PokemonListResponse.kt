package com.example.tp_pokedex.Model.Data.PokemonList

import com.squareup.moshi.Json

data class PokemonListResponse(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url")
    val url: String
)