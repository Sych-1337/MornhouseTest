package com.sych.mornhousetest

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var numberTextView: TextView
    private lateinit var factTextView: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        numberTextView = findViewById(R.id.numberTextView)
        factTextView = findViewById(R.id.factTextView)
        backButton = findViewById(R.id.backButton)

        val number = intent.getStringExtra("number")
        val fact = intent.getStringExtra("fact")

        numberTextView.text = number
        factTextView.text = fact

        backButton.setOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
