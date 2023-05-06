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
import com.example.sample1.models.StudentModel
import com.google.firebase.database.FirebaseDatabase

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var tvSId: TextView
    private lateinit var tvSName: TextView
    private lateinit var tvUni: TextView
    private lateinit var tvStdID: TextView
    private lateinit var tvsNic:TextView
    private lateinit var tvphone:TextView
    private lateinit var tvemail:TextView
    private lateinit var tvsamuDhi:TextView
    private lateinit var tvhousenum:TextView


    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    private lateinit var Calbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("SID").toString(),
                intent.getStringExtra("SName").toString()
            )
        }
        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("SID").toString()
            )
        }



    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Student_Details").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Student data deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvSId = findViewById(R.id.tvSId)
        tvSName = findViewById(R.id.tvSname)
        tvUni = findViewById(R.id.tvUni)
        tvStdID = findViewById(R.id.tvStdID)
        tvsNic = findViewById(R.id.tvNic)
        tvphone=findViewById(R.id.tvPhone)
        tvemail=findViewById(R.id.tvEmail)
        tvsamuDhi=findViewById(R.id.tvSamur)
        tvhousenum = findViewById(R.id.tvhousenum)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvSId.text = intent.getStringExtra("SID")
        tvSName.text = intent.getStringExtra("SName")
        tvUni.text = intent.getStringExtra("Univ")
        tvStdID.text = intent.getStringExtra("StudentID")
        tvsNic.text = intent.getStringExtra("NIC")
        tvphone.text = intent.getStringExtra("Phone")
        tvemail.text = intent.getStringExtra("Email")
        tvsamuDhi.text=intent.getStringExtra("Samurdhi")
        tvhousenum.text=intent.getStringExtra("HouseNumber")

    }

    private fun openUpdateDialog(
        sId: String,
        sname: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val etsname = mDialogView.findViewById<EditText>(R.id.etSname)
        val etuni = mDialogView.findViewById<EditText>(R.id.etUni)
        val etstdid = mDialogView.findViewById<EditText>(R.id.etStid)
        val etnic = mDialogView.findViewById<EditText>(R.id.etNic)
        val etphone = mDialogView.findViewById<EditText>(R.id.etPhone)
        val etemail = mDialogView.findViewById<EditText>(R.id.etEmail)
        val etsamurdhi = mDialogView.findViewById<EditText>(R.id.etSamurdhi)
        val ethousenum = mDialogView.findViewById<EditText>(R.id.etHousenum)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etsname.setText(intent.getStringExtra("SName").toString())
        etuni.setText(intent.getStringExtra("Univ").toString())
        etstdid.setText(intent.getStringExtra("StudentID").toString())
        etnic.setText(intent.getStringExtra("NIC").toString())
        etphone.setText(intent.getStringExtra("Phone").toString())
        etemail.setText(intent.getStringExtra("Email").toString())
        etsamurdhi.setText(intent.getStringExtra("Samurdhi").toString())
        ethousenum.setText(intent.getStringExtra("HouseNumber").toString())

        mDialog.setTitle("Updating $sname Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateEmpData(
                sId,
                etsname.text.toString(),
                etuni.text.toString(),
                etstdid.text.toString(),
                etnic.text.toString(),
                etphone.text.toString(),
                etemail.text.toString(),
                etsamurdhi.text.toString(),
                ethousenum.text.toString(),



            )
            Toast.makeText(applicationContext, "Student data updated", Toast.LENGTH_LONG).show()

            tvSName.text = etsname.text.toString()
            tvUni.text = etuni.text.toString()
            tvStdID.text = etstdid.text.toString()
            tvsNic.text = etnic.text.toString()
            tvphone.text = etphone.text.toString()
            tvemail.text = etemail.text.toString()
            tvsamuDhi.text = etsamurdhi.text.toString()
            tvhousenum.text = ethousenum.text.toString()


            alertDialog.dismiss()
        }

    }
       private fun updateEmpData(
           id:String,
           name:String,
           uni:String,
           stid:String,
           nic:String,
           phone:String,
           email:String,
           samur:String,
           housenum:String,

       ){
           val dbRef = FirebaseDatabase.getInstance().getReference("Student_Details").child(id)
           val empInfo = StudentModel(id, name, uni, stid,nic,phone,email,samur,housenum)
           dbRef.setValue(empInfo)
        }
}