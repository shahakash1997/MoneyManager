package co.asinfotech.mymoneymanager.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseSubType(
    val name: String,
    @PrimaryKey
    val id: String,
    val createdAt: Long,
)