package be.technifuture.eval.viewModel

import android.content.Context
import android.provider.LiveFolders
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import be.technifuture.eval.db.ExpenseRepository
import be.technifuture.eval.model.Expense
import be.technifuture.eval.model.ExpenseType
import kotlinx.coroutines.flow.Flow

internal class ExpenseListViewModel: ViewModel() {
    // var liveDataBookList: LiveData<List<Expense>>? = null

    fun getBooks(context: Context): LiveData<List<Expense>> {
        return ExpenseRepository.getAllExpense(context)
    }

    fun getTypeById(context: Context, id: Long): LiveData<ExpenseType> {
        return ExpenseRepository.getTypeById(context, id)
    }
}