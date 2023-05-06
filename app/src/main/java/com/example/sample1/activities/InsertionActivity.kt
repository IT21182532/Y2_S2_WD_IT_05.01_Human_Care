package com.example.sample1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sample1.models.EmployeeModel
import com.example.sample1.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {

    private lateinit var etEmpName: EditText
    private lateinit var etAddress: EditText
    private lateinit var etContactN: EditText
    private lateinit var etEmpSalary: EditText
    private lateinit var etEmpSalary2: EditText


    private lateinit var btnSaveData: Button
    private lateinit var nextBtn : Button
    private lateinit var btnFetchData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etEmpName = findViewById(R.id.etEmpName)//ndv
        etAddress = findViewById(R.id.etAddress)
        etContactN = findViewById(R.id.etContactN)
        etEmpSalary = findViewById(R.id.etEmpSalary)
        etEmpSalary2 = findViewById(R.id.etEmpSalary2)

        btnSaveData = findViewById(R.id.btnSaveData)
        nextBtn = findViewById(R.id.nextbtn1)
        btnFetchData = findViewById(R.id.btnFetchData)


//        btnNext = findViewById(R.id.btnNext)
//
////        btnNext = findViewById<Button>(R.id.btnNext)
//        btnNext.setOnClickListener{
//            val intent = Intent(this, IncomeActivity::class.java)
//            startActivity(intent)
//        }

        dbRef = FirebaseDatabase.getInstance().getReference("Requirements")

        btnSaveData.setOnClickListener{
            saveEmployeeData()
        }

        nextBtn.setOnClickListener{
            val intent =Intent(this,MyActivity::class.java)
            startActivity(intent)
        }
        btnFetchData.setOnClickListener{
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }


    }
    private fun saveEmployeeData() {
        //getting values
        val empName = etEmpName.text.toString()
        val Address = etAddress.text.toString()
        val contactNum = etContactN.text.toString()
        val empSalary = etEmpSalary.text.toString()
        val empSalary2 = etEmpSalary2.text.toString()

        if (empName.isEmpty()){
            etEmpName.error = "Please enter Yes/No"
        }
        if (Address.isEmpty()){
            etAddress.error = "Please enter Yes/No"
        }
        if (contactNum.isEmpty()){
            etContactN.error = "Please enter Yes/No"
        }
        if (empSalary.isEmpty()){
            etEmpSalary.error = "Please enter Yes/No"
        }
        if (empSalary2.isEmpty()){
            etEmpSalary2.error = "Please enter Yes/No"
        }


        val empId = dbRef.push().key!!

        val employee = EmployeeModel(empId,empName,Address, contactNum,empSalary, empSalary2 )

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener{
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG ).show()

                etEmpName.text.clear()
                etAddress.text.clear()
                etContactN.text.clear()
                etEmpSalary.text.clear()
                etEmpSalary2.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}