package co.asinfotech.mymoneymanager.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbIncome(
    @PrimaryKey
    val id: String,
    val type :String,
    val creditAccount:String,
)