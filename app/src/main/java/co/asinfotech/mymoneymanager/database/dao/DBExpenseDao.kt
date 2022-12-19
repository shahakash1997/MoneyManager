package co.asinfotech.mymoneymanager.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import co.asinfotech.mymoneymanager.database.tables.DbExpense

@Dao
interface DBExpenseDao {
    @Query("SELECT * from `DbExpense`")
    suspend fun getAllExpenses(): List<DbExpense>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(txn: DbExpense)

    @Update
    suspend fun updated(expense: DbExpense)
}
