package be.technifuture.eval

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifuture.eval.databinding.FragmentHomeBinding
import be.technifuture.eval.model.ExpenseWithType
import be.technifuture.eval.viewModel.ExpenseAdapter
import be.technifuture.eval.viewModel.ExpenseListViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var expListViewModel: ExpenseListViewModel
    private lateinit var adapter: ExpenseAdapter
    //private var listExpense = mutableListOf<ExpenseWithType>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        expListViewModel = ViewModelProvider(this)[ExpenseListViewModel::class.java]
        expListViewModel.getExpense(requireContext()).observeForever {
            updateData(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.addButton.setOnClickListener{
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddFragment())
        }

        return binding.root
    }

    private fun updateData(expense: List<ExpenseWithType>) {
        adapter = ExpenseAdapter(expense.toMutableList())
        binding.recycleView.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        binding.recycleView.adapter = adapter
        adapter.notifyDataSetChanged()
    }


}