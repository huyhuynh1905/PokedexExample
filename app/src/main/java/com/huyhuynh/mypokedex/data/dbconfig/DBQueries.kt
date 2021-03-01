package com.huyhuynh.mypokedex.data.dbconfig

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.huyhuynh.mypokedex.data.model.Pokemon
import java.util.ArrayList

class DBQueries {

    private var context: Context? = null
    private var database: SQLiteDatabase? = null
    private var dbHelper: DBHelper? = null

    constructor(context: Context){
        this.context = context
    }

    @Throws(SQLException::class)
    fun open(): DBQueries {
        dbHelper = context?.let { DBHelper(it) }
        database = dbHelper!!.writableDatabase
        return this
    }

    fun close() {
        dbHelper?.close()
    }

    fun insertPokemon(pokemon: Pokemon): Boolean {
        val values = ContentValues()
        values.put(DBConstants.POKEMON_ID, pokemon.id)
        values.put(DBConstants.POKEMON_NAME, pokemon.name)
        values.put(DBConstants.POKEMON_IMGURL, pokemon.imageurl)
        values.put(DBConstants.POKEMON_DESCRIPTION, pokemon.xdescription)
        values.put(DBConstants.POKEMON_HEIGHT, pokemon.height)
        values.put(DBConstants.POKEMON_WEIGHT, pokemon.weight)
        var typeOfPokemon:String ?=null
        for (item in pokemon.typeofpokemon!!){
            typeOfPokemon= item + ","
        }
        values.put(DBConstants.POKEMON_TYPEOFPOKEMON, typeOfPokemon)
        values.put(DBConstants.POKEMON_HP, pokemon.hp)
        values.put(DBConstants.POKEMON_ATTACK, pokemon.attack)
        values.put(DBConstants.POKEMON_DEFENSE, pokemon.defense)
        values.put(DBConstants.POKEMON_MALE, pokemon.male_percentage)
        values.put(DBConstants.POKEMON_FEMALE, pokemon.female_percentage)
        values.put(DBConstants.POKEMON_CYCLES, pokemon.cycles)
        values.put(DBConstants.POKEMON_EGG, pokemon.egg_groups)
        pokemon.id?.let {
            if(findItemExist(it)) {
                Log.d("indItemExist", "Có tồn tại")
                return database!!.update(DBConstants.POKEMON_TABLE, values, DBConstants.POKEMON_ID,  null) > -1
            } else {
                Log.d("indItemExist", "Không tồn tại")
                return database!!.insert(DBConstants.POKEMON_TABLE, null, values) > -1
            }
        }
        return database!!.insert(DBConstants.POKEMON_TABLE, null, values) > -1
    }


    fun readPokemon(): ArrayList<Pokemon> {
        val list: ArrayList<Pokemon> = ArrayList()
        try {
            var cursor: Cursor?
            database = dbHelper?.readableDatabase
            cursor = database?.rawQuery(DBConstants.SELECT_QUERY, null)
            list.clear()
            cursor?.let {
                if (it.count > 0) {
                    if (it.moveToFirst()) {
                        do {
                            val pokemonId = it.getString(cursor.getColumnIndex(DBConstants.POKEMON_ID))
                            val pokemonName = it.getString(cursor.getColumnIndex(DBConstants.POKEMON_NAME))
                            val pokemonImg = it.getString(cursor.getColumnIndex(DBConstants.POKEMON_IMGURL))
                            val pokemonDescription = it.getString(cursor.getColumnIndex(DBConstants.POKEMON_DESCRIPTION))
                            val pokemonHeight = it.getString(cursor.getColumnIndex(DBConstants.POKEMON_HEIGHT))
                            val pokemonWWeight = it.getString(cursor.getColumnIndex(DBConstants.POKEMON_WEIGHT))
                            val pokemonType:String =
                                it.getString(cursor.getColumnIndex(DBConstants.POKEMON_TYPEOFPOKEMON))
                            var typePokemon: MutableList<String> = pokemonType.split(",") as MutableList<String>
                            typePokemon.removeAt(typePokemon.size)
                            val pokemonHp = it.getInt(cursor.getColumnIndex(DBConstants.POKEMON_HP))
                            val pokemonAtk = it.getInt(cursor.getColumnIndex(DBConstants.POKEMON_ATTACK))
                            val pokemonDef = it.getInt(cursor.getColumnIndex(DBConstants.POKEMON_DEFENSE))
                            val pokemonMale = it.getString(cursor.getColumnIndex(DBConstants.POKEMON_MALE))
                            val pokemonFemale = it.getString(cursor.getColumnIndex(DBConstants.POKEMON_FEMALE))
                            val pokemonCycles = it.getString(cursor.getColumnIndex(DBConstants.POKEMON_CYCLES))
                            val pokemonEgg = it.getString(cursor.getColumnIndex(DBConstants.POKEMON_EGG))

                            val pokemon = Pokemon(pokemonId,pokemonName,pokemonImg,pokemonDescription,
                                pokemonHeight,pokemonWWeight,typePokemon,pokemonHp,pokemonAtk,pokemonDef,pokemonMale,
                                pokemonFemale,pokemonCycles,pokemonEgg)
                            list.add(pokemon)
                        } while (cursor.moveToNext())
                    }
                }

                cursor.close()
            }

        } catch (e: Exception) {
            Log.v("Exception", e.message)
        }
        return list
    }

    private fun findItemExist(code: String):Boolean{
        try {
            var cursor: Cursor?
            database = dbHelper?.readableDatabase
            Log.d("indItemExist", DBConstants.EXIST_QUERY + code + "\'" )
            cursor = database?.rawQuery(DBConstants.EXIST_QUERY + code + "\'", null)
            cursor?.let {
                if (it.count > 0) {
                    return true
                }
                cursor.close()
            }
        } catch (e: Exception) {
            Log.v("Exception", e.message)
            return false
        }
        return false
    }


}