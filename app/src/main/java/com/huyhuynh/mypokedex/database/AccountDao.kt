package com.huyhuynh.mypokedex.database

import AccountZ
import androidx.room.*

@Dao
interface AccountDao {

/*    @Query("SELECT * FROM accountz_az WHERE name LIKE '%' || :name || '%'")
    suspend fun getByName(name: String): List<AccountZ>

    @Query("SELECT * FROM accountz_az")
    fun getAll(): List<AccountZ>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<AccountZ>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AccountZ)

    @Delete
    suspend fun delete(account: AccountZ)*/
}