package com.huyhuynh.mypokedex.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Pokemon : Serializable {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("imageurl")
    var imageurl: String? = null
    @SerializedName("xdescription")
    var xdescription: String? = null
    @SerializedName("ydescription")
    var ydescription: String? = null
    @SerializedName("height")
    var height: String? = null
    @SerializedName("category")
    var category: String? = null
    @SerializedName("weight")
    var weight: String? = null
    @SerializedName("typeofpokemon")
    var typeofpokemon: List<String>? = null
    @SerializedName("weaknesses")
    var weaknesses: List<String>? = null
    @SerializedName("evolutions")
    var evolutions: List<String>? = null
    @SerializedName("abilities")
    var abilities: List<String>? = null
    @SerializedName("hp")
    var hp = 0
    @SerializedName("attack")
    var attack = 0
    @SerializedName("defense")
    var defense = 0
    @SerializedName("special_attack")
    var special_attack = 0
    @SerializedName("special_defense")
    var special_defense = 0
    @SerializedName("speed")
    var speed = 0
    @SerializedName("total")
    var total = 0
    @SerializedName("male_percentage")
    var male_percentage: String? = null
    @SerializedName("female_percentage")
    var female_percentage: String? = null
    @SerializedName("genderless")
    var genderless = 0
    @SerializedName("cycles")
    var cycles: String? = null
    @SerializedName("egg_groups")
    var egg_groups: String? = null
    @SerializedName("evolvedfrom")
    var evolvedfrom: String? = null
    @SerializedName("reason")
    var reason: String? = null
    @SerializedName("base_exp")
    var base_exp: String? = null

    constructor(
        id: String?,
        name: String?,
        imageurl: String?,
        xdescription: String?,
        height: String?,
        weight: String?,
        typeofpokemon: List<String>?,
        hp: Int,
        attack: Int,
        defense: Int,
        male_percentage: String?,
        female_percentage: String?,
        cycles: String?,
        egg_groups: String?
    ) {
        this.id = id
        this.name = name
        this.imageurl = imageurl
        this.xdescription = xdescription
        this.height = height
        this.weight = weight
        this.typeofpokemon = typeofpokemon
        this.hp = hp
        this.attack = attack
        this.defense = defense
        this.male_percentage = male_percentage
        this.female_percentage = female_percentage
        this.cycles = cycles
        this.egg_groups = egg_groups
    }
}
