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
import com.example.sample1.models.EarnerModel
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {

    private lateinit var tvEmpId: TextView
    private lateinit var tvEmpName: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvContact: TextView
    private lateinit var tvDistrict: TextView
    private lateinit var tvMember: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

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
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Employee data deleted", Toast.LENGTH_LONG).show()
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
        tvDistrict = findViewById(R.id.tvDistrict)
        tvMember = findViewById(R.id.tvMember)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvEmpId.text = intent.getStringExtra("empId")
        tvEmpName.text = intent.getStringExtra("empName")
        tvAddress.text = intent.getStringExtra("Address")
        tvContact.text = intent.getStringExtra("Contact")
        tvDistrict.text = intent.getStringExtra("District")
        tvMember.text = intent.getStringExtra("Member")

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
        val etDistrict = mDialogView.findViewById<EditText>(R.id.etDistrict)
        val etMember = mDialogView.findViewById<EditText>(R.id.etMember)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etEmpName.setText(intent.getStringExtra("empName").toString())
        etAddress.setText(intent.getStringExtra("Address").toString())
        etContact.setText(intent.getStringExtra("Contact").toString())
        etDistrict.setText(intent.getStringExtra("District").toString())
        etMember.setText(intent.getStringExtra("Member").toString())

        mDialog.setTitle("Updating $empName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateEmpData(
                empId,
                etEmpName.text.toString(),
                etAddress.text.toString(),
                etContact.text.toString(),
                etDistrict.text.toString(),
                etMember.text.toString()
            )
            Toast.makeText(applicationContext, "Employee data updated", Toast.LENGTH_LONG).show()

            tvEmpName.text = etEmpName.text.toString()
            tvAddress.text = etAddress.text.toString()
            tvContact.text = etContact.text.toString()
            tvDistrict.text = etDistrict.text.toString()
            tvMember.text = etMember.text.toString()

            alertDialog.dismiss()
        }

    }
    private fun updateEmpData(
        id: String,
        name: String,
        address: String,
        contact: String,
        district: String,
        member: String,

    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(id)
        val empInfo = EarnerModel(id, name, address, contact, district, member)
        dbRef.setValue(empInfo)
    }
}