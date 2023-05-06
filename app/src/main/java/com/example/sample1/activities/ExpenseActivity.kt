package com.example.sample1.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sample1.R

class ExpenseActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        val income1 = findViewById<EditText>(R.id.etIncome1)
        val income2 = findViewById<EditText>(R.id.etIncome2)
        val other = findViewById<EditText>(R.id.etOther)

        val resultTextView = findViewById<TextView>(R.id.tvTotal)

        val btnSum = findViewById<Button>(R.id.btnCal)

        //button click event
        btnSum.setOnClickListener {
            val in1 = income1.text.toString().toInt()
            val in2 = income2.text.toString().toInt()
            val oth = other.text.toString().toInt()
            val result = in1 + in2 + oth

            resultTextView.text = result.toString()


        }

    }



}