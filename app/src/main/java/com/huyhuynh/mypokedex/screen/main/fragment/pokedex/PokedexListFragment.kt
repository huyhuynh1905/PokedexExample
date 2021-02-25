package com.huyhuynh.mypokedex.screen.main.fragment.pokedex

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.huyhuynh.mypokedex.BR
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.data.adapter.PokemonAdapter
import com.huyhuynh.mypokedex.data.model.Pokemon
import com.huyhuynh.mypokedex.databinding.FragmentPokedexListBinding
import demo.com.weatherapp.screen.base.fragment.BaseBindingFragment

class PokedexListFragment : BaseBindingFragment<FragmentPokedexListBinding,PokedexListFragmentViewModel>() {
    override val bindingVariable: Int
        get() = BR.pokedexViewModel
    override val viewModel: PokedexListFragmentViewModel
        get() = ViewModelProviders.of(this).get(PokedexListFragmentViewModel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_pokedex_list

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        viewDataBinding?.recyclerView?.apply {
            adapter = PokemonAdapter(viewModel.pokemonList, onItemClick)
            hasFixedSize()
            layoutManager = GridLayoutManager(this@PokedexListFragment.context,2)
        }

    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {

    }

    private val onItemClick = object : PokemonAdapter.OnItemClickListener {
        override fun onClickScan(value: Pokemon) {
            Toast.makeText(this@PokedexListFragment.context,"Click "+value.id,Toast.LENGTH_SHORT).show()
        }

    }

}