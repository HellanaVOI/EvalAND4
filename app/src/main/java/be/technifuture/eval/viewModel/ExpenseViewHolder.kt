package be.technifuture.eval.viewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.technifuture.eval.databinding.CellExpenseBinding
import be.technifuture.eval.model.ExpenseWithType
import java.text.SimpleDateFormat

class ExpenseViewHolder(private var viewBinding: CellExpenseBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(expense: ExpenseWithType) {

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(expense.exp.date)
        //val currentDate = sdf.format(Date())
        //sdf.format(expense.exp.date)


        viewBinding.type.text = expense.type?.name
        viewBinding.name.text = expense.exp.name
        viewBinding.date.text = currentDate
        viewBinding.amount.text = expense.exp.value.toString()
    }
}

class ExpenseAdapter(private var expense: MutableList<ExpenseWithType>) :
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