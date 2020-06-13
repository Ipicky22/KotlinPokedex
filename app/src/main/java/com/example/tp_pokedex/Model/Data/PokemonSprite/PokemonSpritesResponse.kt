package com.example.tp_pokedex.Model.Data.PokemonSprite

import com.squareup.moshi.Json

data class PokemonSpritesResponse (
    @Json(name = "front_default")
    val front_default: String,
    @Json(name = "front_shiny")
    val front_shiny: String
)