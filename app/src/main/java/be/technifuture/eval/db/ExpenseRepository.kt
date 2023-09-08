package be.technifuture.eval.db

import android.content.Context
import androidx.lifecycle.LiveData
import be.technifuture.eval.model.Expense
import be.technifuture.eval.model.ExpenseType
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

                val expense = expDatabase!!.expenseDao().getCount()
                if (expense == 0) {
                    expDatabase!!.expenseDao().insert(Expense(name = "SUSHI", date = 1694069949, value = 50f, type = 0))
                    expDatabase!!.expenseDao().insert(Expense(name = "ROBE", date = 1699343949, value = 14.50f, type = 1))
                    expDatabase!!.expenseDao().insert(Expense(name = "CONVERSE", date = 1573113549, value = 99.50f, type = 2))
                    expDatabase!!.expenseDao().insert(Expense(name = "LESSIVE", date = 1573545549, value = 12.10f, type = 1))
                    expDatabase!!.expenseDao().insert(Expense(name = "AUTRE", date = 1668239949, value = 45f, type = 0))
                    expDatabase!!.expenseDao().insert(Expense(name = "MAQUILLAGE", date = 1641023949, value = 28.99f, type = 2))
                }

            }
            return db
        }

        fun getAllType(context: Context): LiveData<List<ExpenseType>> {
            if(expDatabase == null) {
                expDatabase = initializeDB(context)
            }
            return expDatabase!!.expenseTypeDao().getAll()
        }

        fun getTypeById(context: Context, id: Long): LiveData<ExpenseType> {
            if(expDatabase == null) {
                expDatabase = initializeDB(context)
            }
            return expDatabase!!.expenseTypeDao().getTypeById(id)
        }

        fun getAllExpense(context: Context): LiveData<List<Expense>> {
            if(expDatabase == null) {
                expDatabase = initializeDB(context)
            }
            return expDatabase!!.expenseDao().getAll()
        }

    }
}