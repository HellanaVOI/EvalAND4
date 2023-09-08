package be.technifuture.eval.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.technifuture.eval.model.Expense
import be.technifuture.eval.model.ExpenseType
import be.technifuture.eval.model.TypeWithExpense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expense")
    fun getAll(): Flow<List<Expense>>

    @Query("SELECT COUNT(*) FROM expense")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(type: Expense)


}