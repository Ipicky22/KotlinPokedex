package com.example.tp_pokedex.PokemonDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.tp_pokedex.R
import com.example.tp_pokedex.ViewModel.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_description_pokemon.*

class PokemonDetailFragment: Fragment() {

    private val args: PokemonDetailFragmentArgs by navArgs()

    private val viewModel by lazy {
        ViewModelProvider(activity as AppCompatActivity).get(PokemonViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPokemonDescription(args.pokemonId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemonDescription.observe(viewLifecycleOwner, Observer { pokemonDetail ->
            pokemon_detail_name.text = pokemonDetail.name
        })
    }
}
