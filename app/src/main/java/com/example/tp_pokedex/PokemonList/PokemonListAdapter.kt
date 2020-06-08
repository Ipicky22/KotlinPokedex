package com.example.tp_pokedex.PokemonList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp_pokedex.Data.PokemonListResponse
import com.example.tp_pokedex.R
import kotlinx.android.synthetic.main.item_pokemon.view.*
import kotlin.properties.Delegates

class PokemonListAdapter() : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    var list: List<PokemonListResponse> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var onClickListener: (String) -> Unit = { }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: PokemonListResponse) {
            itemView.pokemon_name.text = pokemon.name
            itemView.pokemon_type.text = pokemon.url

            val idPokemon = pokemon.url.split("/")[6]
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

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(list[position])
    }
}