package com.huyhuynh.mypokedex.database

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.huyhuynh.mypokedex.data.model.Account

@Database(entities = [Account::class], version = 1)
abstract class MyDataBase : RoomDatabase() {

    abstract fun accountDao(): AccountDao


    suspend fun clearData() {
        //accountDao().deleteAll()
    }

    companion object {
        @Volatile
        private var INSTANCE: MyDataBase? = null
        fun getDatabase(context: Context): MyDataBase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDataBase::class.java,
                    "my_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}