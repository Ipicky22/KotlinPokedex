package com.example.tp_pokedex.View.PokemonList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp_pokedex.Model.Data.PokemonListResponse
import com.example.tp_pokedex.R
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonPagedListAdapter() : PagedListAdapter<PokemonListResponse, PokemonPagedListAdapter.PokemonViewHolder>(
    DIFF_CALLBACK
) {

    var onClickListener: (String) -> Unit = { }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: PokemonListResponse) {
            val idPokemon = pokemon.url.split("/")[6]

            if (idPokemon.length == 1) {
                itemView.pokemon_name.text = "#00" + idPokemon + "  " + pokemon.name.capitalize()
            }

            if (idPokemon.length == 2) {
                itemView.pokemon_name.text = "#0" + idPokemon + "  " + pokemon.name.capitalize()
            }

            if (idPokemon.length == 3) {
                itemView.pokemon_name.text = "#" + idPokemon + "  " + pokemon.name.capitalize()
            }

            Glide.with(this.itemView)
                .load("https://pokeres.bastionbot.org/images/pokemon/${idPokemon}.png")
                .into(this.itemView.pokemon_image)

            itemView.pokemon_description.setOnClickListener   { onClickListener.invoke(idPokemon) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        getItem(position).let { pokemon ->
            if (pokemon != null) holder.bind(pokemon)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<PokemonListResponse>() {
            override fun areItemsTheSame(oldPokemon: PokemonListResponse,
                                         newPokemon: PokemonListResponse
            ) = oldPokemon == newPokemon

            override fun areContentsTheSame(oldPokemon: PokemonListResponse,
                                            newPokemon: PokemonListResponse
            ) = oldPokemon == newPokemon
        }
    }

}