package com.example.tp_pokedex.Model.Data

import com.squareup.moshi.Json

data class PokemonDetailResponse (
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "height")
    val height: String,
    @Json(name = "weight")
    val weight: String,
    @Json(name = "types")
    val types: List<PokemonTypesResponse>,
    @Json(name = "sprites")
    val sprites: PokemonSpritesResponse,
    @Json(name = "stats")
    val stats: List<PokemonStatsResponse>,
    @Json(name = "color")
    val color: ColorResponse
)