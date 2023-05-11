package com.example.sample1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.sample1.R
import com.example.sample1.models.EmployeeModel
import com.google.firebase.database.FirebaseDatabase

class RequirementsDetailsActivity : AppCompatActivity() {

    private lateinit var tvRREQ1: TextView
    private lateinit var tvRREQ2: TextView
    private lateinit var tvRREQ3: TextView
    private lateinit var tvRREQ4: TextView
    private lateinit var tvRREQ5: TextView
    private lateinit var tvRREQ6: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requirements_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("REQ1").toString(),
                intent.getStringExtra("REQ2").toString()
            )
        }
        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("REQ1").toString()
            )
        }

    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Requirements").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Requirements data deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvRREQ1 = findViewById(R.id.tvEmpId)
        tvRREQ2 = findViewById(R.id.tvEmpName)
        tvRREQ3 = findViewById(R.id.tvAddress)
        tvRREQ4 = findViewById(R.id.tvContact)
        tvRREQ5 = findViewById(R.id.tvEmpSalary)
        tvRREQ6 = findViewById(R.id.tvEmpSalary2)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvRREQ1.text = intent.getStringExtra("REQ1")
        tvRREQ2.text = intent.getStringExtra("REQ2")
        tvRREQ3.text = intent.getStringExtra("REQ3")
        tvRREQ4.text = intent.getStringExtra("REQ4")
        tvRREQ5.text = intent.getStringExtra("REQ5")
        tvRREQ6.text = intent.getStringExtra("REQ6")

    }

    private fun openUpdateDialog(
        empId: String,
        empName: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val etEmpName = mDialogView.findViewById<EditText>(R.id.etEmpName)
        val etAddress = mDialogView.findViewById<EditText>(R.id.etAddress)
        val etContact = mDialogView.findViewById<EditText>(R.id.etContactN)
        val etEmpSalary = mDialogView.findViewById<EditText>(R.id.etEmpSalary)
        val etEmpSalary2 = mDialogView.findViewById<EditText>(R.id.etEmpSalary2)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etEmpName.setText(intent.getStringExtra("REQ2").toString())
        etAddress.setText(intent.getStringExtra("REQ3").toString())
        etContact.setText(intent.getStringExtra("REQ4").toString())
        etEmpSalary.setText(intent.getStringExtra("REQ5").toString())
        etEmpSalary2.setText(intent.getStringExtra("REQ6").toString())

        mDialog.setTitle("Updating $empName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateREQData(
                empId,
                etEmpName.text.toString(),
                etAddress.text.toString(),
                etContact.text.toString(),
                etEmpSalary.text.toString(),
                etEmpSalary2.text.toString()
            )
            Toast.makeText(applicationContext, "Requirements data updated", Toast.LENGTH_LONG).show()

            tvRREQ2.text = etEmpName.text.toString()
            tvRREQ3.text = etAddress.text.toString()
            tvRREQ4.text = etContact.text.toString()
            tvRREQ5.text = etEmpSalary.text.toString()
            tvRREQ6.text = etEmpSalary2.text.toString()

            alertDialog.dismiss()
        }

    }
    private fun updateREQData(
        REQ1: String,
        REQ2: String,
        REQ3: String,
        REQ4: String,
        REQ5: String,
        REQ6: String,

    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Requirements").child(REQ1)
        val REQInfo = EmployeeModel(REQ1, REQ2, REQ3, REQ4, REQ5, REQ6)
        dbRef.setValue(REQInfo)
    }
}