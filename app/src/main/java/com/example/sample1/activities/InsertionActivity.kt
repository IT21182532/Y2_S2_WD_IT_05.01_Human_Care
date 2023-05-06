package com.example.sample1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sample1.models.EarnerModel
import com.example.sample1.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {

    private lateinit var etEmpName: EditText
    private lateinit var etAddress: EditText
    private lateinit var etContactN: EditText
    private lateinit var etDistrict: EditText
    private lateinit var etMember: EditText


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
        etDistrict = findViewById(R.id.etDistrict)
        etMember = findViewById(R.id.etMember)

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

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

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
        val District = etDistrict.text.toString()
        val Member = etMember.text.toString()

        if (empName.isEmpty()){
            etEmpName.error = "Please enter name"
        }
        if (Address.isEmpty()){
            etAddress.error = "Please enter Address"
        }
        if (contactNum.isEmpty()){
            etContactN.error = "Please enter age"
        }
        if (District.isEmpty()){
            etDistrict.error = "Please enter salary"
        }
        if (Member.isEmpty()){
            etMember.error = "Please enter salary 2"
        }


        val empId = dbRef.push().key!!

        val employee = EarnerModel(empId,empName,Address, contactNum,District, Member )

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener{
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG ).show()

                etEmpName.text.clear()
                etAddress.text.clear()
                etContactN.text.clear()
                etDistrict.text.clear()
                etMember.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}