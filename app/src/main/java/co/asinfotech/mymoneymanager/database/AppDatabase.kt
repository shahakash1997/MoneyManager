package co.asinfotech.mymoneymanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.asinfotech.mymoneymanager.database.dao.TransactionDao
import co.asinfotech.mymoneymanager.database.tables.Transaction

@Database(
    entities = [Transaction::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}