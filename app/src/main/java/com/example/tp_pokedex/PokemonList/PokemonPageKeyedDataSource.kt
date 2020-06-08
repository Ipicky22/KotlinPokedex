package com.example.tp_pokedex.PokemonList


import androidx.paging.PageKeyedDataSource
import com.example.tp_pokedex.Data.PokemonListResponse
import com.example.tp_pokedex.Network.Api
import kotlinx.coroutines.CoroutineScope

class PokemonPageKeyedDataSource(private val scope: CoroutineScope) : PageKeyedDataSource<Int, PokemonListResponse>() {

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PokemonListResponse>
    ) {}

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PokemonListResponse>
    ) {
        // Charger la page 0, utiliser la callback avec la valeur 1 comme "key"
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PokemonListResponse>) {
        val currentPage = params.key
        val itemsPerPage = params.requestedLoadSize
        Api.pokemonWebService.getPaginationPokemons(limit = itemsPerPage, offset = currentPage * itemsPerPage)
    }
}