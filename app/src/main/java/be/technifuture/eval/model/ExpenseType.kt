package be.technifuture.eval.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class ExpenseType(
    @PrimaryKey(autoGenerate = true)
    val typeId: Long = 0,
    val name: String? = null

)

data class TypeWithExpense(
    @Embedded val type: ExpenseType,
    @Relation(
        parentColumn = "typeId",
        entityColumn = "expId"
    )
    val listGame: List<Expense>
)