package com.example.tp_pokedex.PokemonDetail

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tp_pokedex.R
import com.example.tp_pokedex.ViewModel.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_description_pokemon.*
import kotlinx.android.synthetic.main.fragment_description_pokemon.view.*
import kotlinx.android.synthetic.main.item_pokemon.*
import kotlinx.android.synthetic.main.item_pokemon.view.*

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
            pokemon_detail_id.text = pokemonDetail.id
            pokemon_detail_name.text = pokemonDetail.name
            pokemon_detail_height.text = pokemonDetail.height
            pokemon_detail_weight.text = pokemonDetail.weight
            pokemon_type_1.text = pokemonDetail.types[0].type.name

            if ( pokemonDetail.types.size > 1 ) {
                pokemon_type_2.text = pokemonDetail.types[1].type.name
            }

            //Log.d("color", pokemonDetail.color.name)
            //Color_view.setBackgroundColor(Color.parseColor("#ffffff"))

            Glide.with(view)
                .load(pokemonDetail.sprites.front_default)
                .into(view.pokemon_sprite_default)

            Glide.with(view)
                .load(pokemonDetail.sprites.front_shiny)
                .into(view.pokemon_sprite_shiny)
        })
    }
}
