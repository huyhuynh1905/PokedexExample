package com.huyhuynh.mypokedex.screen.main.fragment.pokemon

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.huyhuynh.mypokedex.BR
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.data.dbconfig.DBHelper
import com.huyhuynh.mypokedex.data.dbconfig.DBQueries
import com.huyhuynh.mypokedex.data.model.Pokemon
import com.huyhuynh.mypokedex.databinding.FragmentPokemonDetailsBinding
import demo.com.weatherapp.screen.base.fragment.BaseBindingFragment

class PokemonDetailsFragment : BaseBindingFragment<FragmentPokemonDetailsBinding,PokemonDetailsFragmentViewModel>() {


    override val bindingVariable: Int
        get() = BR.pokemonViewModel
    override val viewModel: PokemonDetailsFragmentViewModel
        get() = ViewModelProviders.of(this).get(PokemonDetailsFragmentViewModel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_pokemon_details


    var dbHelper: DBHelper? = null
    var dbQueries: DBQueries? = null
    var pokemon: Pokemon?=null
    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        pokemon = arguments?.getSerializable("pokemon") as Pokemon?
        pokemon?.let {
            viewModel.getPokemonObject(it)
        }
        var context = this@PokemonDetailsFragment.context?.applicationContext
        dbHelper = context?.let { DBHelper(it) }
        dbQueries = context?.let { DBQueries(it) }
        viewDataBinding?.btnDelete?.setOnClickListener {
            dbQueries!!.open()
            pokemon?.id?.let {
                dbQueries!!.deletePokemon(it)
            }
            dbQueries!!.close()
            findNavController().navigate(R.id.action_pokemonDetailsFragment_to_pokedexListFragment)
            this.onDetach()
        }
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {

    }
}