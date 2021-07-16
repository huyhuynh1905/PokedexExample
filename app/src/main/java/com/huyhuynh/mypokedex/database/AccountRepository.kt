package com.huyhuynh.mypokedex.database

import AccountZ
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class AccountRepository(private val account: AccountDao) {

/*    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(accountc: AccountZ) {
        account.insert(accountc)
    }

    @WorkerThread
    suspend fun getByName(name: String): List<AccountZ>? {
        return account.getByName(name)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(accountc: AccountZ) {
        account.delete(accountc)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(list: List<AccountZ>) {
        account.insertAll(list)
    }*/

    companion object {
        @Volatile
        private var INSTANCE: AccountRepository? = null

        fun getInstance(bankInfoDAO: AccountDao): AccountRepository {
            if (INSTANCE == null)  // NOT thread safe!
                INSTANCE = AccountRepository(bankInfoDAO)
            return INSTANCE as AccountRepository
        }
    }
}