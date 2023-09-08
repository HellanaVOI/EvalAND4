package be.technifuture.eval

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import be.technifuture.eval.databinding.FragmentAddBinding
import be.technifuture.eval.db.ExpenseRepository
import be.technifuture.eval.model.Expense
import be.technifuture.eval.model.ExpenseType

class AddFragment : Fragment() {

    private lateinit var types: List<ExpenseType>
    private var selectedType: Long = 0
    lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater)

        ExpenseRepository.getAllType(requireContext()).observe(viewLifecycleOwner) {
            this.types = it
        }


        binding.addButton.setOnClickListener{
            if (binding.titleText.text.isNullOrEmpty() ||
                binding.amountText.text.isNullOrEmpty() ||
                binding.dateText.text.isNullOrEmpty()
                ){

            }else{
                addExpense(binding.titleText.text.toString(), binding.dateText.text.toString(), binding.amountText.text.toString(), selectedType)
            }

            findNavController().navigateUp()
        }

        binding.typeText.setOnClickListener {
            val array = types.map { it.name }.toTypedArray()
            val builder = AlertDialog.Builder(requireContext())
            val tmpGenres: ArrayList<ExpenseType> = arrayListOf()
            builder.setTitle("Choose a Type.")
            builder.setSingleChoiceItems(array, -1) { dialog, i ->
                binding.typeText.text = types[i].name
                selectedType = types[i].typeId
                dialog.dismiss()
            }
            builder.setNeutralButton("Cancel") { dialog, which ->
                // Do something when click the neutral button
                dialog.cancel()
            }
            val dialog = builder.create()
            dialog.show()
        }

        return binding.root
    }



    private fun addExpense(name: String,
                           date: String,
                           value: String,
                           type: Long,){
        ExpenseRepository.insertExpense(requireContext(), name, date.toLong(), value.toFloat(), type)
    }
}