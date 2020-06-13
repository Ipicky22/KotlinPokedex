package com.example.tp_pokedex.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_pokedex.Model.Data.PokemonColor.PokemonColorsResponse
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.example.tp_pokedex.Model.Data.PokemonDetail.PokemonDetailResponse
import com.example.tp_pokedex.Model.Data.PokemonList.PokemonListResponse
import com.example.tp_pokedex.View.PokemonList.PokemonPageKeyedDataSource
import com.example.tp_pokedex.Model.Repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    private val pokemonRepository =
        PokemonRepository()

    private val _pokemonDetail = MutableLiveData<PokemonDetailResponse>()
    val pokemonDetail: LiveData<PokemonDetailResponse> = _pokemonDetail

    private val _pokemonColor = MutableLiveData<PokemonColorsResponse>()
    val pokemonColor: LiveData<PokemonColorsResponse> = _pokemonColor

    fun loadPokemonDescription(id: String) {
        viewModelScope.launch {
            pokemonRepository.getPokemonDescription(id)?.let {
                _pokemonDetail.value = it
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

