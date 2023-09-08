package be.technifuture.eval.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.technifuture.eval.model.ExpenseType
import be.technifuture.eval.model.TypeWithExpense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseTypeDao {
    @Query("SELECT COUNT(*) FROM ExpenseType")
    fun getCount(): Int

    @Query("SELECT * FROM ExpenseType")
    fun getAll(): Flow<List<ExpenseType>>

    @Query("SELECT * FROM ExpenseType")
    fun getExpense(): Flow<List<TypeWithExpense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(type: ExpenseType)
}