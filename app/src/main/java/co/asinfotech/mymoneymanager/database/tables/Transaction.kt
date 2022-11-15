package co.asinfotech.mymoneymanager.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(
    @PrimaryKey
    val id: Int,
    val date : Long,
    val amount : Double,
    val description:String,
    val type:String,
    val bankAccountName:String?,
    val subDescription:String,

)