package be.technifuture.eval.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


//TODO: Passer du TimeStand en Date
@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val expId: Long = 0,
    val date: Long? = null,
    val value: Float? = null
)