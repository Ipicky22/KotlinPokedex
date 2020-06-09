package com.example.tp_pokedex.PokemonDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import com.bumptech.glide.Glide
import com.example.tp_pokedex.R
import com.example.tp_pokedex.ViewModel.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_description_pokemon.*
import kotlinx.android.synthetic.main.fragment_description_pokemon.view.*
import kotlinx.android.synthetic.main.item_pokemon.view.*
import java.lang.Integer.parseInt


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
            val idPokemon = pokemonDetail.id

            if (parseInt(idPokemon) < 10) {
                pokemon_detail_name.text = "#00" + idPokemon + "  " + pokemonDetail.name.capitalize()
            }

            else if (parseInt(idPokemon) < 100) {
                pokemon_detail_name.text = "#0" + idPokemon + "  " + pokemonDetail.name.capitalize()
            }

            else if (parseInt(idPokemon) < 1000) {
                pokemon_detail_name.text = "#" + idPokemon + "  " + pokemonDetail.name.capitalize()
            }

            pokemon_detail_height.text = pokemonDetail.height
            pokemon_detail_weight.text = pokemonDetail.weight
            pokemon_type_1.text = pokemonDetail.types[0].type.name

            pokemonDetail.stats.forEach{  stat ->
                when(stat.stat.name) {
                    "hp" -> {
                        pokemon_stat_hp.text = stat.base_stat
                    }
                    "attack" -> {
                        pokemon_stat_attack.text = stat.base_stat
                    }
                    "defense" -> {
                        pokemon_stat_defense.text = stat.base_stat
                    }
                    "special-attack" -> {
                        pokemon_stat_special_attack.text = stat.base_stat
                    }
                    "special-defense" -> {
                        pokemon_stat_special_defense.text = stat.base_stat
                    }
                    "speed" -> {
                        pokemon_stat_speed.text = stat.base_stat
                    }
                }
            }

            if ( pokemonDetail.types.size > 1 ) {
                pokemon_type_2.text = pokemonDetail.types[1].type.name
            }

            Glide.with(view)
                .load(pokemonDetail.sprites.front_default)
                .into(view.pokemon_sprite_default)

            Glide.with(view)
                .load(pokemonDetail.sprites.front_shiny)
                .into(view.pokemon_sprite_shiny)
        })
    }
}
