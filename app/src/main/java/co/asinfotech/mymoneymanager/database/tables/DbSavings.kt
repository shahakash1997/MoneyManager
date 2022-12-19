package co.asinfotech.mymoneymanager.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DbSavings(
    @PrimaryKey
    val id: String
)
