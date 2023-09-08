package be.technifuture.eval.viewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.technifuture.eval.databinding.CellExpenseBinding
import be.technifuture.eval.model.Expense

class ExpenseViewHolder(private var viewBinding: CellExpenseBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(expense: Expense) {

        viewBinding.type.text = ""
        viewBinding.name.text = expense.name
        viewBinding.date.text = expense.date.toString()
        viewBinding.amount.text = expense.value.toString()
    }
}

class CardListAdapter(private var expense: MutableList<Expense>) :
    RecyclerView.Adapter<ExpenseViewHolder>() {
    private lateinit var binding: CellExpenseBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseViewHolder {
        binding = CellExpenseBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(expense[position])
    }
    override fun getItemCount(): Int {
        return expense.size
    }
}