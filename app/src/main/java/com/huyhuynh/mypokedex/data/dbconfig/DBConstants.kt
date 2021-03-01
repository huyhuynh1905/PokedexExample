package com.huyhuynh.mypokedex.data.dbconfig

internal object DBConstants {
    const val POKEMON_TABLE = "pokemon"
    const val POKEMON_ID = "pokemon_id"
    const val POKEMON_NAME = "pokemon_name"
    const val POKEMON_IMGURL = "pokemon_imageurl"
    const val POKEMON_DESCRIPTION = "pokemon_description"
    const val POKEMON_HEIGHT = "pokemon_height"
    const val POKEMON_WEIGHT = "pokemon_weight"
    const val POKEMON_TYPEOFPOKEMON = "pokemon_typeofpokemon"
    const val POKEMON_HP = "pokemon_hp"
    const val POKEMON_ATTACK = "pokemon_attack"
    const val POKEMON_DEFENSE = "pokemon_defense"
    const val POKEMON_MALE = "pokemon_male_percentage"
    const val POKEMON_FEMALE = "pokemon_female_percentage"
    const val POKEMON_CYCLES = "pokemon_cycles"
    const val POKEMON_EGG = "pokemon_egg_groups"


    const val CREATE_POKEMON_TABLE = ("CREATE TABLE IF NOT EXISTS " + POKEMON_TABLE + " ("
            + POKEMON_ID + " TEXT PRIMARY KEY,"
            + POKEMON_NAME + " TEXT,"
            + POKEMON_IMGURL + " TEXT,"
            + POKEMON_DESCRIPTION + " TEXT,"
            + POKEMON_HEIGHT + " TEXT,"
            + POKEMON_WEIGHT + " TEXT,"
            + POKEMON_TYPEOFPOKEMON + " TEXT,"
            + POKEMON_HP + " INTEGER,"
            + POKEMON_ATTACK + " INTEGER,"
            + POKEMON_DEFENSE + " INTEGER,"
            + POKEMON_MALE + " TEXT,"
            + POKEMON_FEMALE + " TEXT,"
            + POKEMON_CYCLES + " TEXT,"
            + POKEMON_EGG + " TEXT)")

    const val SELECT_QUERY = "SELECT * FROM " + POKEMON_TABLE

    const val EXIST_QUERY = "SELECT " + POKEMON_ID + " FROM " + POKEMON_TABLE + " WHERE " + POKEMON_ID + "=\'"
}