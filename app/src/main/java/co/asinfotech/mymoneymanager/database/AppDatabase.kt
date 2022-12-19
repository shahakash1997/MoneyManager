package co.asinfotech.mymoneymanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.asinfotech.mymoneymanager.database.dao.DBExpenseDao
import co.asinfotech.mymoneymanager.database.tables.DbExpense

@Database(
    entities = [DbExpense::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expensesDao(): DBExpenseDao
}