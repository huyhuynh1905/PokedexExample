package com.huyhuynh.mypokedex.screen.main.fragment.pokedex

import android.annotation.SuppressLint
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.huyhuynh.mypokedex.data.model.Pokemon
import com.huyhuynh.mypokedex.data.repository.PokemonRepository
import demo.com.weatherapp.data.source.remote.BaseApi
import demo.com.weatherapp.screen.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokedexListFragmentViewModel @Inject constructor(): BaseViewModel() {
    var pokemonList: ObservableList<Pokemon> = ObservableArrayList()
    private var repository: PokemonRepository ?=null
    val loading = MutableLiveData<Boolean>()
    init {
        loading.value = false
        repository = PokemonRepository(BaseApi().providerPokedexApi())
        loadData()
    }

    @SuppressLint("CheckResult")
    fun loadData() {
        loading.postValue(false)

        repository!!.getPokemon().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe(
                {it -> handleResponse(it)},
                {it -> handleError(it)}
            )

        loading.value = false
    }

    private fun handleResponse(it: List<Pokemon>?) {
        loading.value = false
        pokemonList.clear()
        it?.let { it1 -> pokemonList.addAll(it1) }
    }

    private fun handleError(message: Throwable) {
        loading.value = false
        message.printStackTrace()
    }
}