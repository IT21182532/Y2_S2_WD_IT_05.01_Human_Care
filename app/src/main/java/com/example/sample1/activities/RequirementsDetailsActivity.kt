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

    private lateinit var tvEmpId: TextView
    private lateinit var tvEmpName: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvContact: TextView
    private lateinit var tvEmpSalary: TextView
    private lateinit var tvEmpSalary2: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requirements_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("empId").toString(),
                intent.getStringExtra("empName").toString()
            )
        }
        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("empId").toString()
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
        tvEmpId = findViewById(R.id.tvEmpId)
        tvEmpName = findViewById(R.id.tvEmpName)
        tvAddress = findViewById(R.id.tvAddress)
        tvContact = findViewById(R.id.tvContact)
        tvEmpSalary = findViewById(R.id.tvEmpSalary)
        tvEmpSalary2 = findViewById(R.id.tvEmpSalary2)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvEmpId.text = intent.getStringExtra("empId")
        tvEmpName.text = intent.getStringExtra("empName")
        tvAddress.text = intent.getStringExtra("Address")
        tvContact.text = intent.getStringExtra("Contact")
        tvEmpSalary.text = intent.getStringExtra("empSalary")
        tvEmpSalary2.text = intent.getStringExtra("empSalary2")

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

        etEmpName.setText(intent.getStringExtra("empName").toString())
        etAddress.setText(intent.getStringExtra("Address").toString())
        etContact.setText(intent.getStringExtra("Contact").toString())
        etEmpSalary.setText(intent.getStringExtra("empSalary").toString())
        etEmpSalary2.setText(intent.getStringExtra("empSalary2").toString())

        mDialog.setTitle("Updating $empName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateEmpData(
                empId,
                etEmpName.text.toString(),
                etAddress.text.toString(),
                etContact.text.toString(),
                etEmpSalary.text.toString(),
                etEmpSalary2.text.toString()
            )
            Toast.makeText(applicationContext, "Requirements data updated", Toast.LENGTH_LONG).show()

            tvEmpName.text = etEmpName.text.toString()
            tvAddress.text = etAddress.text.toString()
            tvContact.text = etContact.text.toString()
            tvEmpSalary.text = etEmpSalary.text.toString()
            tvEmpSalary2.text = etEmpSalary2.text.toString()

            alertDialog.dismiss()
        }

    }
    private fun updateEmpData(
        id: String,
        name: String,
        address: String,
        contact: String,
        salary: String,
        salary2: String,

    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Requirements").child(id)
        val empInfo = EmployeeModel(id, name, address, contact, salary, salary2)
        dbRef.setValue(empInfo)
    }
}