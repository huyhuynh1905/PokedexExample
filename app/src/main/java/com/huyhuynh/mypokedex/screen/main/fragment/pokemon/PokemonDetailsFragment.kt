package com.huyhuynh.mypokedex.screen.main.fragment.pokemon

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.huyhuynh.mypokedex.BR
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.data.dbconfig.DBHelper
import com.huyhuynh.mypokedex.data.dbconfig.DBQueries
import com.huyhuynh.mypokedex.data.model.Pokemon
import com.huyhuynh.mypokedex.databinding.FragmentPokemonDetailsBinding
import com.huyhuynh.mypokedex.screen.utils.CheckNetwork
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
        //nhận data từ fragment trước
        pokemon = arguments?.getSerializable("pokemon") as Pokemon?
        pokemon?.let {
            viewModel.getPokemonObject(it)
        }

        //bắt sự kiện delete, chỉ dùng cho trường hợp database
        val context = activity?.applicationContext
        dbHelper = context?.let { DBHelper(it) }
        dbQueries = context?.let { DBQueries(it) }
        viewDataBinding?.btnDelete?.setOnClickListener {
            if (CheckNetwork.isConnectedNetwork()){
                Toast.makeText(activity,"Chức năng chỉ sử dụng được khi không có kết nối mạng!",Toast.LENGTH_LONG).show()
            } else {
                dbQueries?.open()
                pokemon?.id?.let {
                    dbQueries?.deletePokemon(it)
                }
                dbQueries?.close()
                activity?.onBackPressed()
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {

    }


}