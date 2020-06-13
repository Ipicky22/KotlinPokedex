package com.example.tp_pokedex.View.PokemonDetail

import android.graphics.Color.parseColor
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tp_pokedex.Model.Data.PokemonDetail.PokemonDetailResponse
import com.example.tp_pokedex.R
import com.example.tp_pokedex.ViewModel.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_description_pokemon.*
import kotlinx.android.synthetic.main.fragment_description_pokemon.view.*
import java.lang.Integer.parseInt


class PokemonDetailFragment: Fragment() {

    private val args: PokemonDetailFragmentArgs by navArgs()

    private val viewModel by lazy {
        ViewModelProvider(activity as AppCompatActivity).get(PokemonViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPokemonDescription(args.pokemonId)
        viewModel.loadPokemonColor(args.pokemonId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description_pokemon, container, false)
    }

    private fun prefixName(idPokemon: String, pokemonDetail: PokemonDetailResponse) {
        if (parseInt(idPokemon) < 10) {
            pokemon_detail_name.text = "#00" + idPokemon + "  " + pokemonDetail.name.capitalize()
        }

        else if (parseInt(idPokemon) < 100) {
            pokemon_detail_name.text = "#0" + idPokemon + "  " + pokemonDetail.name.capitalize()
        }

        else if (parseInt(idPokemon) < 1000) {
            pokemon_detail_name.text = "#" + idPokemon + "  " + pokemonDetail.name.capitalize()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemonColor.observe(viewLifecycleOwner, Observer { color ->
            val couleur_pokemon = color.color.name.toUpperCase()
            tableauColorView.setBackgroundResource(R.drawable.shape)
            val bgShape = tableauColorView.background.current as GradientDrawable

            if (couleur_pokemon != "BROWN" && couleur_pokemon != "PINK") {
                bgShape.setColor(parseColor(couleur_pokemon));
            } else {
                bgShape.setColor(parseColor("#09867A"));
            }
        })

        viewModel.pokemonDetail.observe(viewLifecycleOwner, Observer { pokemonDetail ->
            val idPokemon = pokemonDetail.id

            prefixName(idPokemon, pokemonDetail)

            pokemon_detail_weight.text = pokemonDetail.weight
            pokemon_type_1.text = pokemonDetail.types[0].type.name.capitalize()
            if ( pokemonDetail.types.size > 1 ) {
                pokemon_type_2.text = pokemonDetail.types[1].type.name.capitalize()
            }

            pokemonDetail.stats.forEach { stat ->
                when (stat.stat.name) {
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

            Glide.with(view)
                .load(pokemonDetail.sprites.front_default)
                .into(view.pokemon_sprite_default)

            Glide.with(view)
                .load(pokemonDetail.sprites.front_shiny)
                .into(view.pokemon_sprite_shiny)
        })


    }
}
