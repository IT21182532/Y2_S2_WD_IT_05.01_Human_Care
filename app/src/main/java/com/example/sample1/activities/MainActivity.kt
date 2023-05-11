package com.example.sample1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sample1.R

class MainActivity : AppCompatActivity() {

    private lateinit var unibutton : Button
    private lateinit var lowerbutton :Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lowerbutton = findViewById(R.id.lowerbtn)
        unibutton = findViewById(R.id.unibtn)

        unibutton.setOnClickListener{
            val intent = Intent(this, TwobtnviewActivity::class.java)
            startActivity(intent)
        }
        lowerbutton.setOnClickListener{
            val intent = Intent(this, InsertionActivityLI::class.java)
            startActivity(intent)
        }
//
//        btnFetchData.setOnClickListener{
//            val intent = Intent(this, FetchingActivity::class.java)
//            startActivity(intent)
//        }
    }
}