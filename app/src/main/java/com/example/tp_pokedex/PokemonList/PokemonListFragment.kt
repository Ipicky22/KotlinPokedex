package com.example.tp_pokedex.PokemonList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp_pokedex.R
import com.example.tp_pokedex.ViewModel.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import androidx.navigation.fragment.findNavController

class PokemonListFragment : Fragment() {

    private val adapter = PokemonListAdapter()
    private val viewModel by lazy {
        ViewModelProvider(activity as AppCompatActivity).get(PokemonViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(activity)

        viewModel.pagedList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        adapter.onClickListener = { pokemon ->
            val action = PokemonListFragmentDirections.DescriptionPokemon(pokemon)
            findNavController().navigate(action)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == INFO_POKEMON_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                recyclerview.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.pagedList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    companion object {
        const val INFO_POKEMON_REQUEST_CODE= 200
    }

}