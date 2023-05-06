package com.example.sample1.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sample1.R

class IncomeActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        val income1 = findViewById(R.id.etIncome1) as EditText
        val income2 = findViewById(R.id.etIncome2) as EditText
        val other = findViewById(R.id.etOther) as EditText

        val resultTextView = findViewById<TextView>(R.id.tvResult)

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