package co.asinfotech.mymoneymanager.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.asinfotech.mymoneymanager.database.tables.Transaction

@Dao
interface TransactionDao {
    @Query("SELECT * from `Transaction`")
    suspend fun getAllTransactions(): List<Transaction>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(txn: Transaction)
}
