package com.example.tp_pokedex.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.example.tp_pokedex.Data.PokemonDetailResponse
import com.example.tp_pokedex.Data.PokemonListResponse
import com.example.tp_pokedex.PokemonList.PokemonPageKeyedDataSource
import com.example.tp_pokedex.PokemonList.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    private val pokemonRepository = PokemonRepository()

    private val _pokemonDescription = MutableLiveData<PokemonDetailResponse>()
    val pokemonDescription: LiveData<PokemonDetailResponse> = _pokemonDescription

    val pagedList =
        LivePagedListBuilder(object : DataSource.Factory<Int, PokemonListResponse>() {
            override fun create(): DataSource<Int, PokemonListResponse> = PokemonPageKeyedDataSource(viewModelScope)
        }, PER_PAGE).build()

    fun loadPokemonDescription(id: String) {
        viewModelScope.launch {
            pokemonRepository.getPokemonDescription(id)?.let {
                _pokemonDescription.value = it
            }
        }
    }

    companion object {
        private const val PER_PAGE = 20
    }
}

