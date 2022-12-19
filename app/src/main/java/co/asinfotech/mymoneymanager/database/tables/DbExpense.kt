package co.asinfotech.mymoneymanager.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbExpense(
    @PrimaryKey
    val id: String,
    val dateTime: Long,
    val amount: Long,
    val description: String,
    val location: String,
    val createdAt: Long,
    val updatedAt: Long,
    val createdBy: String,
    val updatedBy: String,
    val txnNote: String?,
    val subtype: String

)
