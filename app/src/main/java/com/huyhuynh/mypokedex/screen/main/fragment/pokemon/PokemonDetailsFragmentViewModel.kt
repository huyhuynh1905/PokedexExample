package com.huyhuynh.mypokedex.screen.main.fragment.pokemon

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.huyhuynh.mypokedex.data.model.Pokemon
import demo.com.weatherapp.screen.base.viewmodel.BaseViewModel
import java.util.ArrayList
import javax.inject.Inject

class PokemonDetailsFragmentViewModel @Inject constructor() : BaseViewModel(){

    var name : ObservableField<String> = ObservableField()
    var typeofpokemon : List<String> = ArrayList()
    var imageurl :ObservableField<String> = ObservableField()

    var xdescription : ObservableField<String> = ObservableField()
    var height : ObservableField<String> = ObservableField()
    var weight : ObservableField<String> = ObservableField()
    var hp : ObservableInt = ObservableInt()
    var attack : ObservableInt = ObservableInt()
    var defense : ObservableInt = ObservableInt()
    var hptext : ObservableField<String> = ObservableField()
    var attacktext : ObservableField<String> = ObservableField()
    var defensetext : ObservableField<String> = ObservableField()
    var percentage : ObservableField<String> = ObservableField()
    var egg_groups : ObservableField<String> = ObservableField()
    var cycles : ObservableField<String> = ObservableField()

    //\u2641 87.5%  \u2640 12.5%


    init {
        loadData()
    }

    @SuppressLint("CheckResult")
    fun loadData() {
    }

    fun getPokemonObject(pokemon: Pokemon){
        name.set(pokemon.name)
        typeofpokemon = pokemon.typeofpokemon!!
        imageurl.set(pokemon.imageurl)
        xdescription.set(pokemon.xdescription)
        height.set(pokemon.height)
        weight.set(pokemon.weight)
        hp.set(pokemon.hp)
        attack.set(pokemon.attack)
        defense.set(pokemon.defense)
        attacktext.set(pokemon.attack.toString())
        hptext.set(pokemon.hp.toString())
        defensetext.set(pokemon.defense.toString())
        val perStr = "\u2641 ${pokemon?.male_percentage}  \u2640 ${pokemon?.female_percentage}"
        percentage.set(perStr)
        egg_groups.set(pokemon.egg_groups)
        cycles.set(pokemon.cycles)
    }

}