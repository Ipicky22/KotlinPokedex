package com.example.tp_pokedex.PokemonList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_pokedex.Data.PokemonListResponse
import com.example.tp_pokedex.R
import kotlinx.android.synthetic.main.item_pokemon.view.*
import kotlin.properties.Delegates

class PokemonListAdapter() : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    var list: List<PokemonListResponse> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var onClickListener: (PokemonListResponse) -> Unit = { }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: PokemonListResponse) {
            itemView.findViewById<TextView>(R.id.pokemon_nom).text = pokemon.name
            itemView.findViewById<TextView>(R.id.pokemon_type).text = pokemon.url

            itemView.pokemon_description.setOnClickListener   { onClickListener.invoke(pokemon) }
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