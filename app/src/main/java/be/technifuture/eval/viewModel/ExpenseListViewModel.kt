package be.technifuture.eval.viewModel

import android.content.Context
import android.provider.LiveFolders
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import be.technifuture.eval.db.ExpenseRepository
import be.technifuture.eval.model.Expense
import be.technifuture.eval.model.ExpenseType
import be.technifuture.eval.model.ExpenseWithType
import kotlinx.coroutines.flow.Flow

internal class ExpenseListViewModel: ViewModel() {

    fun getExpense(context: Context): LiveData<List<ExpenseWithType>> {
        return ExpenseRepository.getAllExpense(context)
    }
}