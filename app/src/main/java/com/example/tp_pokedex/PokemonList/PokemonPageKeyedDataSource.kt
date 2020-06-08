package com.example.tp_pokedex.PokemonList

import androidx.paging.PageKeyedDataSource
import com.example.tp_pokedex.Data.PokemonListResponse
import com.example.tp_pokedex.Network.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PokemonPageKeyedDataSource(private val scope: CoroutineScope) : PageKeyedDataSource<Int, PokemonListResponse>() {

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PokemonListResponse>
    ) {}

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PokemonListResponse>
    ) {
        scope.launch {
            val response = Api.pokemonWebService.getPaginationPokemons(limit = 20, offset =  0)
            callback.onResult(response.body()!!.results, null, 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PokemonListResponse>) {
        val currentPage = params.key
        val itemsPerPage = params.requestedLoadSize
        scope.launch {
            val response = Api.pokemonWebService.getPaginationPokemons(limit = itemsPerPage, offset = currentPage * itemsPerPage)
            callback.onResult(response.body()!!.results, currentPage + 1)
        }
    }
}