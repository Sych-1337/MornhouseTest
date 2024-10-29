package com.sych.mornhousetest

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: FactViewModel by viewModels()

    private lateinit var numberEditText: EditText
    private lateinit var getFactButton: Button
    private lateinit var getRandomFactButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        numberEditText = findViewById(R.id.numberEditText)
        getFactButton = findViewById(R.id.getFactButton)
        getRandomFactButton = findViewById(R.id.getRandomFactButton)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = FactAdapter { fact ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("number", fact.number)
            intent.putExtra("fact", fact.fact)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.allFacts.observe(this, Observer { facts ->
            facts?.let {
                adapter.submitList(it)
            }
        })

        getFactButton.setOnClickListener {
            val number = numberEditText.text.toString()
            if (number.isNotEmpty()) {
                fetchFact(number)
            } else {
                Toast.makeText(this, "Введіть число", Toast.LENGTH_SHORT).show()
            }
        }

        getRandomFactButton.setOnClickListener {
            fetchRandomFact()
        }
    }

    private fun fetchFact(number: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getFact(number)
                if (response.isSuccessful) {
                    val factText = response.body()
                    factText?.let {
                        val fact = FactEntity(number = number, fact = it)
                        viewModel.insertFact(fact)
                        openDetailActivity(fact)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Помилка отримання факту", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Помилка: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openDetailActivity(fact: FactEntity) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("number", fact.number)
            putExtra("fact", fact.fact)
        }
        startActivity(intent)
    }


    private fun fetchRandomFact() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getRandomFact()
                if (response.isSuccessful) {
                    val factText = response.body()
                    factText?.let {
                        val number = it.substringBefore(" ").trim()
                        val fact = FactEntity(number = number, fact = it)
                        viewModel.insertFact(fact)
                        openDetailActivity(fact)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Помилка отримання факту", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Помилка: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
