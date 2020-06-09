package com.example.tp_pokedex.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
<<<<<<< HEAD
import com.example.tp_pokedex.Data.PokemonColorResponse
=======
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
>>>>>>> e05a32a75cc1fcaa1f6d0041b99703778517d19d
import com.example.tp_pokedex.Data.PokemonDetailResponse
import com.example.tp_pokedex.Data.PokemonListResponse
import com.example.tp_pokedex.PokemonList.PokemonPageKeyedDataSource
import com.example.tp_pokedex.PokemonList.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    private val pokemonRepository = PokemonRepository()

    private val _pokemonDescription = MutableLiveData<PokemonDetailResponse>()
    val pokemonDescription: LiveData<PokemonDetailResponse> = _pokemonDescription

    private val _pokemonColor = MutableLiveData<PokemonColorResponse>()
    val pokemonColor: LiveData<PokemonColorResponse> = _pokemonColor

    fun loadPokemon() {
        viewModelScope.launch {
            pokemonRepository.refresh()?.let{
                _pokemonList.value = it.results
            }
        }
    }

    fun loadPokemonDescription(id: String) {
        viewModelScope.launch {
            pokemonRepository.getPokemonDescription(id)?.let {
                _pokemonDescription.value = it
            }
            pokemonRepository.getPokemonColor(id)?.let {
                _pokemonColor.value = it
            }
        }
    }

    val pagedList =
        LivePagedListBuilder(object : DataSource.Factory<Int, PokemonListResponse>() {
            override fun create(): DataSource<Int, PokemonListResponse> = PokemonPageKeyedDataSource(viewModelScope)
        }, PER_PAGE).build()

    companion object {
        private const val PER_PAGE = 20
    }
}

