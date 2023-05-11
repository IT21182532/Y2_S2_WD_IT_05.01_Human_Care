package com.example.sample1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.sample1.R

class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        val income1 = findViewById(R.id.etIncome1) as EditText
        val income2 = findViewById(R.id.etIncome2) as EditText
        val other = findViewById(R.id.etOther) as EditText

        val resultTextView = findViewById(R.id.tvResultview) as TextView

        val btnSum = findViewById<Button>(R.id.btnCal)
        val nxt = findViewById<Button>(R.id.btnxtB)

        //button click event
        btnSum.setOnClickListener {
            val in1 = income1.text.toString().toInt()
            val in2 = income2.text.toString().toInt()
            val oth = other.text.toString().toInt()
            val result = in1 + in2 + oth

            resultTextView.text = result.toString()


        }
        nxt.setOnClickListener{
            val intent = Intent(this, CalActivity::class.java)
            startActivity(intent)
        }

    }
}