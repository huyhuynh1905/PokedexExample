package com.huyhuynh.mypokedex.data.model

import com.google.gson.annotations.SerializedName

class Pokedex {
    @SerializedName("pokemon")
    var pokemon: List<Pokemon>? = null
}