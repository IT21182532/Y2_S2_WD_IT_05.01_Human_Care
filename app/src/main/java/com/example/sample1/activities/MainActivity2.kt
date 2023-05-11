package com.example.sample1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sample1.R

class MainActivity2 : AppCompatActivity() {

    private lateinit var btnInsertData : Button
    private lateinit var btnFetchData : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnInsertData = findViewById(R.id.btnInsertData)
        btnFetchData = findViewById(R.id.btnFetchData)



        btnInsertData.setOnClickListener{
            val intent = Intent(this, InsertionActivityLI::class.java)
            startActivity(intent)
        }

        btnFetchData.setOnClickListener{
            val intent = Intent(this, FetchingActivityLI::class.java)
            startActivity(intent)
        }



    }
}