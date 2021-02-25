package com.huyhuynh.mypokedex.data.api

import com.huyhuynh.mypokedex.data.model.Pokemon
import io.reactivex.Observable
import retrofit2.http.GET

interface PokemonAPI {
    @GET("pokemon.json")
    fun getPokedex(): Observable<List<Pokemon>>

}