package com.huyhuynh.mypokedex.data.repository

import com.huyhuynh.mypokedex.data.api.PokemonAPI
import com.huyhuynh.mypokedex.data.model.Pokemon
import io.reactivex.Observable
import javax.inject.Inject

class PokemonRepository @Inject constructor(val pokemonAPI: PokemonAPI)  {
    fun getPokemon(): Observable<List<Pokemon>> = pokemonAPI.getPokedex()
}