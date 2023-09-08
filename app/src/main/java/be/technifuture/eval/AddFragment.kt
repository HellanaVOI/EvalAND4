package be.technifuture.eval

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import be.technifuture.eval.databinding.FragmentAddBinding
import be.technifuture.eval.db.ExpenseRepository
import be.technifuture.eval.model.ExpenseType
import java.util.Calendar

class AddFragment : Fragment(), DatePickerDialog.OnDateSetListener{

    private lateinit var types: List<ExpenseType>
    private var selectedType: Long = 0
    var calendar = Calendar.getInstance()
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
                binding.amountText.text.isNullOrEmpty()
                ){

            }else{
                addExpense(binding.titleText.text.toString(), calendar.timeInMillis, binding.amountText.text.toString().toFloat(), selectedType)
            }

            findNavController().navigateUp()
        }

        binding.typeText.setOnClickListener {
            val array = types.map { it.name }.toTypedArray()
            val builder = AlertDialog.Builder(requireContext())
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

        binding.dateText.setOnClickListener{
                val calendar: Calendar = Calendar.getInstance()
                val datePickerDialog =
                    DatePickerDialog(requireContext(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
                datePickerDialog.show()
            }


        return binding.root
    }

    private fun addExpense(name: String,
                           date: Long,
                           value: Float,
                           type: Long,){
        ExpenseRepository.insertExpense(requireContext(), name, date, value, type)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        binding.dateText.text = "$day / $month / $year"
        Log.d("DEBUG", calendar.timeInMillis.toString())
    }

}