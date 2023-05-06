package com.example.sample1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sample1.models.StudentModel
import com.example.sample1.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {

    private lateinit var stdname: EditText
    private lateinit var uni: EditText
    private lateinit var studentID: EditText
    private lateinit var Nic : EditText
    private lateinit var Phone : EditText
    private lateinit var Email:EditText
    private lateinit var Samurdhi:EditText
    private lateinit var Housenumber : EditText


    private lateinit var btnSaveData: Button
    private lateinit var Calbtn:Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        stdname = findViewById(R.id.name)//ndv
        uni = findViewById(R.id.univname)
        studentID = findViewById(R.id.sId)
        Nic = findViewById(R.id.nic)
        Phone = findViewById(R.id.phnNum)
        Email =findViewById(R.id.Email)
        Samurdhi = findViewById(R.id.SBH)
        Housenumber = findViewById(R.id.houseNmbr)


        btnSaveData = findViewById(R.id.btnSaveData)
        Calbtn =findViewById(R.id.calbutton)

        dbRef = FirebaseDatabase.getInstance().getReference("Student_Details")

        btnSaveData.setOnClickListener{
            saveStudentData()
        }

        Calbtn.setOnClickListener{

            val intent = Intent(this, CalActivity::class.java)
            startActivity(intent)
        }



    }
    private fun saveStudentData() {
        //getting values
        val stdName = stdname.text.toString()
        val uniN = uni.text.toString()
        val sId = studentID.text.toString()
        val NIC = Nic.text.toString()
        val phone = Phone.text.toString()
        val email = Email.text.toString()
        val samur = Samurdhi.text.toString()
        val housenum = Housenumber.text.toString()

        if (stdName.isEmpty()){
            stdname.error = "Please enter name"
        }
        if (uniN.isEmpty()){
            uni.error = "Please enter University Name"
        }
        if (sId.isEmpty()){
            studentID.error = "Please enter Student ID"
        }

        if (NIC.isEmpty()){
            Nic.error = "Please enter NIC Number"
        }
        if (phone.isEmpty()){
            Phone.error = "Please enter Phone NUmber"
        }
        if (email.isEmpty()){
            Email.error = "Please enter Email Adderess"
        }
        if (samur.isEmpty()){
            Samurdhi.error = "Please enter Samurdhi Beneficiary Housing or not"
        }
        if (housenum.isEmpty()){
            Housenumber.error = "Please enter House Number"
        }
        val Stid = dbRef.push().key!!

        val student = StudentModel(Stid,stdName,uniN,sId,NIC,phone,email,samur,housenum)

        dbRef.child(Stid).setValue(student)
            .addOnCompleteListener{
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG ).show()

                stdname.text.clear()
                uni.text.clear()
                studentID.text.clear()
                Nic.text.clear()
                Phone.text.clear()
                Email.text.clear()
                Samurdhi.text.clear()
                Housenumber.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}