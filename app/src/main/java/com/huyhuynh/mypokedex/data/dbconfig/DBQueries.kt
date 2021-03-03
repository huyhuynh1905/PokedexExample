package com.huyhuynh.mypokedex.data.dbconfig

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.huyhuynh.mypokedex.data.model.Pokemon
import java.util.ArrayList

class DBQueries (context: Context) {

    private var database: SQLiteDatabase? = null
    private var dbHelper: DBHelper? = DBHelper.getInstance(context)

    companion object{
        private var instance : DBQueries?=null
        fun getInstance(context: Context):DBQueries?{
            if (instance==null){
                instance = DBQueries(context)
            }
            return instance
        }
    }

    @Throws(SQLException::class)
    fun open(): DBQueries {
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
        var typeOfPokemon = pokemon.typeofpokemon.toString().substring(1,pokemon.typeofpokemon.toString().length-1)
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
                return database!!.update(DBConstants.POKEMON_TABLE, values, DBConstants.POKEMON_ID,  null) > -1
            } else {
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
            Log.d("readPokemon", e.message)
        }
        return list
    }

    private fun findItemExist(code: String):Boolean{
        try {
            var cursor: Cursor?
            database = dbHelper?.readableDatabase
            cursor = database?.rawQuery(DBConstants.EXIST_QUERY + code + "\'", null)
            cursor?.let {
                if (it.count > 0) {
                    return true
                }
                cursor.close()
            }
        } catch (e: Exception) {
            Log.d("findItemExist", e.message)
            return false
        }
        return false
    }

    fun deletePokemon(pokemonId: String) : Boolean{
        if (findItemExist(pokemonId)) {
            return database!!.delete(
                DBConstants.POKEMON_TABLE,
                DBConstants.POKEMON_ID + "= \'" + pokemonId+"\'", null
            ) > -1
        }
        return false
    }

}