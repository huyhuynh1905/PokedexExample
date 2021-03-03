package com.huyhuynh.mypokedex.screen.main.fragment.pokedex

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.huyhuynh.mypokedex.data.dbconfig.DBHelper
import com.huyhuynh.mypokedex.data.dbconfig.DBQueries
import com.huyhuynh.mypokedex.data.model.Pokemon
import com.huyhuynh.mypokedex.data.repository.PokemonRepository
import demo.com.weatherapp.MainApplication
import demo.com.weatherapp.data.source.remote.BaseApi
import demo.com.weatherapp.screen.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokedexListFragmentViewModel @Inject constructor(): BaseViewModel() {
    var pokemonList: ObservableList<Pokemon> = ObservableArrayList()
    private var repository: PokemonRepository ?=null
    val loading = MutableLiveData<Boolean>()
    var email:String?=null

    //
    var context: Context? = MainApplication.getContextInstance()
    //var dbHelper: DBHelper? = null
    var dbQueries: DBQueries? = null
    init {
        //

        //
        loading.value = false
        repository = PokemonRepository(BaseApi().providerPokedexApi())
        //loadData()
    }

    @SuppressLint("CheckResult")
    fun loadData() {
        loading.postValue(true)

        if (email=="huy") {
            Log.d("loadData","Chạy true")
            Log.d("loadData",email.toString())
            repository!!.getPokemon().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(
                    {handleResponse(it)},
                    {handleError(it)}
                )

        } else {
            Log.d("loadData",email.toString())
            Log.d("loadData","Chạy else")
            loadFromDataBase()
        }
        loading.value = false
    }

    private fun loadFromDataBase() {
        loading.value = false
        context?.let {
            DBQueries.getInstance(it)!!.readPokemon().let {
                pokemonList.addAll(it)
            }
        }
    }

    private fun handleResponse(it: List<Pokemon>?) {
        loading.value = false
        pokemonList.clear()
        it?.let {
            it1 -> pokemonList.addAll(it1)
        }
        pokemonList?.let {
            context?.let {
                dbQueries = DBQueries(it)
                dbQueries?.open()
                for (itemPokemon in pokemonList){
                    dbQueries?.insertPokemon(itemPokemon)
                }
                dbQueries?.close()
            }

        }
    }

    private fun handleError(message: Throwable) {
        loading.value = false
        message.printStackTrace()
    }

/*    var iscall = false
    fun createData(){
        if(pokemonList.size <= 0) {
            if (!iscall) {
                iscall = true
                dbHelper = context?.let { DBHelper(it) }
                dbQueries = context?.let { DBQueries(it) }
            }
        }
    }*/

}