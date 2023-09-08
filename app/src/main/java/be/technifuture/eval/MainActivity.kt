package be.technifuture.eval

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import be.technifuture.eval.databinding.ActivityMainBinding
import be.technifuture.eval.db.ExpenseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            ExpenseRepository.getAllExpense(this@MainActivity).collect { list ->
                list.forEach { item ->
                    item.value?.let { it1 -> Log.d("DEBUGG", it1.toString()) }
                    item.date?.let { it1 -> Log.d("DEBUGG", it1.toString()) }
                }
            }
        }
    }
}