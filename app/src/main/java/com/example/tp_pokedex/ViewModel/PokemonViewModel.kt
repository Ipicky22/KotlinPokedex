package com.example.tp_pokedex.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_pokedex.Model.Data.PokemonColorResponse
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.example.tp_pokedex.Model.Data.PokemonDetailResponse
import com.example.tp_pokedex.Model.Data.PokemonListResponse
import com.example.tp_pokedex.View.PokemonList.PokemonPageKeyedDataSource
import com.example.tp_pokedex.Model.Repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    private val pokemonRepository =
        PokemonRepository()

    private val _pokemonDescription = MutableLiveData<PokemonDetailResponse>()
    val pokemonDescription: LiveData<PokemonDetailResponse> = _pokemonDescription

    private val _pokemonColor = MutableLiveData<PokemonColorResponse>()
    val pokemonColor: LiveData<PokemonColorResponse> = _pokemonColor

    fun loadPokemonDescription(id: String) {
        viewModelScope.launch {
            pokemonRepository.getPokemonDescription(id)?.let {
                _pokemonDescription.value = it
            }
        }
    }

    fun loadPokemonColor(id: String){
        viewModelScope.launch {
            pokemonRepository.getPokemonColor(id)?.let {
                _pokemonColor.value = it
            }
        }
    }

    val pagedList =
        LivePagedListBuilder(object : DataSource.Factory<Int, PokemonListResponse>() {
            override fun create(): DataSource<Int, PokemonListResponse> =
                PokemonPageKeyedDataSource(
                    viewModelScope
                )
        }, PER_PAGE).build()

    companion object {
        private const val PER_PAGE = 20
    }
}

