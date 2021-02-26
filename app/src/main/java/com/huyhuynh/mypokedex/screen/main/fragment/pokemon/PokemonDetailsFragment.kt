package com.huyhuynh.mypokedex.screen.main.fragment.pokemon

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.huyhuynh.mypokedex.BR
import com.huyhuynh.mypokedex.R
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

    var pokemon: Pokemon?=null
    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        pokemon = arguments?.getSerializable("pokemon") as Pokemon?
        pokemon?.let {
            viewModel.getPokemonObject(it)
        }
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {

    }
}