package com.example.sample1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.sample1.R

class MyActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my2)

        val ex1 = findViewById<TextView>(R.id.etFood1)
        val ex2 = findViewById<TextView>(R.id.etUtility1)
        val ex3 = findViewById<TextView>(R.id.etMedical1)
        val ex4 = findViewById<TextView>(R.id.etEdu1)
        val ex5 = findViewById<TextView>(R.id.etTransport1)

        val btnSum = findViewById<Button>(R.id.btnAdd)
        val nxtbtn = findViewById<Button>(R.id.btnNxt)
        val b2 = findViewById<Button>(R.id.btnAdd2)
        //val number1 = findViewById<EditText>(R.id.etNumber1)
        //val number2 = findViewById<EditText>(R.id.etNumber2)
        val resultTextView = findViewById<TextView>(R.id.tvResult)
        //val btnSum = findViewById<Button>(R.id.btnAdd)

        btnSum.setOnClickListener{
            //val n1 = number1.text.toString().toInt()
            //val n2 = number2.text.toString().toInt()
            //val result = n1 + n2
            //resultTextView.text = result.toString()
            val e1 = ex1.text.toString().toInt()
            val e2 = ex2.text.toString().toInt()
            val e3 = ex3.text.toString().toInt()
            val e4 = ex4.text.toString().toInt()
            val e5 = ex5.text.toString().toInt()

            val result = e1+e2+e3+e4+e5
            resultTextView.text = result.toString()
        }

        b2.setOnClickListener{

            ex1.setText("")
            ex2.setText("")
            ex3.setText("")
            ex4.setText("")
            ex5.setText("")
        }

        nxtbtn.setOnClickListener{
            val intent = Intent(this, MyActivity::class.java)
            startActivity(intent)
        }

    }
    }
