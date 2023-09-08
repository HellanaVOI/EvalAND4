package be.technifuture.eval.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.technifuture.eval.dao.ExpenseDao
import be.technifuture.eval.dao.ExpenseTypeDao
import be.technifuture.eval.model.Expense
import be.technifuture.eval.model.ExpenseType


@Database(entities = [Expense::class, ExpenseType::class], version = 1)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun expenseTypeDao(): ExpenseTypeDao
    companion object {

        @Volatile
        private var sharedInstance: ExpenseDatabase? = null

        fun getDB(context: Context) : ExpenseDatabase {
            if (sharedInstance != null) return sharedInstance!!
            synchronized(this) {
                sharedInstance = Room
                    .databaseBuilder(context, ExpenseDatabase::class.java, "local.db")
                    .fallbackToDestructiveMigration()
                    .build()
                return sharedInstance!!
            }
        }
    }
}