package be.technifuture.eval.db

import android.content.Context
import androidx.lifecycle.LiveData
import be.technifuture.eval.model.Expense
import be.technifuture.eval.model.ExpenseType
import be.technifuture.eval.model.ExpenseWithType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ExpenseRepository {
    companion object {
        var expDatabase: ExpenseDatabase? = null

        fun initializeDB(context: Context): ExpenseDatabase {
            val db = ExpenseDatabase.getDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val type = expDatabase!!.expenseTypeDao().getCount()
                if (type == 0) {
                    expDatabase!!.expenseTypeDao().insert(ExpenseType(name = "RESTAURANT"))
                    expDatabase!!.expenseTypeDao().insert(ExpenseType(name = "CLOTHE"))
                    expDatabase!!.expenseTypeDao().insert(ExpenseType(name = "BILL"))
                    expDatabase!!.expenseTypeDao().insert(ExpenseType(name = "SHOP"))
                }
            }
            return db
        }

        fun getAllType(context: Context): LiveData<List<ExpenseType>> {
            if (expDatabase == null) {
                expDatabase = initializeDB(context)
            }
            return expDatabase!!.expenseTypeDao().getAll()
        }

        fun getTypeById(context: Context, id: Long): LiveData<ExpenseType> {
            if (expDatabase == null) {
                expDatabase = initializeDB(context)
            }
            return expDatabase!!.expenseTypeDao().getTypeById(id)
        }

        fun getAllExpense(context: Context): LiveData<List<ExpenseWithType>> {
            if (expDatabase == null) {
                expDatabase = initializeDB(context)
            }
            return expDatabase!!.expenseDao().getAll()
        }

        fun insertExpense(
            context: Context,
            name: String,
            date: Long,
            value: Float,
            type: Long,
        ) {
            expDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val exp =
                    Expense(name = name, date = date, value = value, type = type)
                val bookId = expDatabase!!.expenseDao().insert(exp)
            }
        }

    }
}