package com.example.tp_pokedex.Model.Data

import com.example.tp_pokedex.Model.Data.PokemonStatResponse
import com.squareup.moshi.Json

data class PokemonStatsResponse (
    @Json(name="base_stat")
    val base_stat: String,
    @Json(name="stat")
    val stat: PokemonStatResponse
)