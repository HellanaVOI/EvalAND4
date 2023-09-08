package be.technifuture.eval

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import be.technifuture.eval.databinding.FragmentAddBinding
import be.technifuture.eval.db.ExpenseRepository
import be.technifuture.eval.model.ExpenseType

class AddFragment : Fragment() {

    private lateinit var types: List<ExpenseType>
    private lateinit var selectedType: ExpenseType
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
                binding.dateText.text.isNullOrEmpty() ||
                binding.typeText.text.isNullOrEmpty()){


            }else{
                addExpense()
            }
        }

        binding.typeText.setOnClickListener {
            val array = types.map { it.name }.toTypedArray()
            val builder = AlertDialog.Builder(requireContext())
            val tmpGenres: ArrayList<ExpenseType> = arrayListOf()
            builder.setTitle("Choose a Type.")
            builder.setSingleChoiceItems(array, -1) { dialog, i ->
                binding.typeText.text = types[i].name
                selectedType = types[i]
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

    fun addExpense(){

    }
}