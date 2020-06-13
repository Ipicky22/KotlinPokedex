package com.example.tp_pokedex.View.PokemonList

import androidx.paging.PageKeyedDataSource
import com.example.tp_pokedex.Model.Data.PokemonList.PokemonListResponse
import com.example.tp_pokedex.Model.Repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PokemonPageKeyedDataSource(private val scope: CoroutineScope) : PageKeyedDataSource<Int, PokemonListResponse>() {

    private val _pokemonRepository =
        PokemonRepository()

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PokemonListResponse>
    ) {}

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PokemonListResponse>
    ) {
        scope.launch {
            val response = _pokemonRepository.getPokemonWithPagination(limit = 20, offset =  0)
            callback.onResult(response!!.results, null, 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PokemonListResponse>) {
        val currentPage = params.key
        val itemsPerPage = params.requestedLoadSize
        scope.launch {
            val response = _pokemonRepository.getPokemonWithPagination(limit = itemsPerPage, offset = currentPage * itemsPerPage)
            callback.onResult(response!!.results, currentPage + 1)
        }
    }
}