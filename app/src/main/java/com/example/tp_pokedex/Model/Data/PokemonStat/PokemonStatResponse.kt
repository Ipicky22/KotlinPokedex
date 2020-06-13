package com.example.tp_pokedex.Model.Data.PokemonStat

import com.squareup.moshi.Json

data class PokemonStatResponse (
    @Json(name="name")
    val name: String
)
