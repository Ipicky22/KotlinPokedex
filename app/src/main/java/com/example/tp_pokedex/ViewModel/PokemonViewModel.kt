package com.example.tp_pokedex.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_pokedex.Data.PokemonColorResponse
import com.example.tp_pokedex.Data.PokemonDetailResponse
import com.example.tp_pokedex.Data.PokemonListResponse
import com.example.tp_pokedex.PokemonList.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    private val pokemonRepository =
        PokemonRepository()

    private val _pokemonList = MutableLiveData<List<PokemonListResponse>>()
    val pokemonList: LiveData<List<PokemonListResponse>> = _pokemonList

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
}

