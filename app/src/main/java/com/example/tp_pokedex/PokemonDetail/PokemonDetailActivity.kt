package com.example.tp_pokedex.PokemonDetail

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tp_pokedex.Data.PokemonListResponse
import com.example.tp_pokedex.R
import com.example.tp_pokedex.ViewModel.PokemonViewModel

class PokemonDetailActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_description_pokemon)

        val infoPokemon = intent.getSerializableExtra("infoPokemon") as PokemonListResponse
        val idPokemon: String = infoPokemon.url.split('/')[6]

        viewModel.loadPokemonDescription(idPokemon)

        viewModel.pokemonDescription.observe(this, androidx.lifecycle.Observer { pokeDetail ->
            findViewById<TextView>(R.id.pokemon_name).text = pokeDetail.name
        })
    }



}