package com.example.sample1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sample1.R
import com.example.sample1.adapters.StdAdapter
import com.example.sample1.models.StudentModel
import com.google.firebase.database.*

class FetchingActivity : AppCompatActivity() {

    private lateinit var stdRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var stdList: ArrayList<StudentModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        stdRecyclerView = findViewById(R.id.rvstd)
        stdRecyclerView.layoutManager = LinearLayoutManager(this)
        stdRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        stdList = arrayListOf<StudentModel>()
        getEmployeesData()
    }
    private fun getEmployeesData(){
        stdRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Student_Details")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                stdList.clear()
                if(snapshot.exists()){
                    for(stdSnap in snapshot.children){
                        val stdData = stdSnap.getValue(StudentModel::class.java)
                        stdList.add(stdData!!)
                    }
                    val mAdapter = StdAdapter(stdList)
                    stdRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : StdAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@FetchingActivity, StudentDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("SID", stdList[position].sId)
                           intent.putExtra("SName", stdList[position].sName)
                           intent.putExtra("Univ", stdList[position].uniname)
                            intent.putExtra("StudentID", stdList[position].stdid)
                           intent.putExtra("NIC", stdList[position].nic)
                            intent.putExtra("Phone", stdList[position].phone)
                            intent.putExtra("Email", stdList[position].email)
                            intent.putExtra("Samurdhi", stdList[position].samurdhi)
                            intent.putExtra("HouseNumber",stdList[position].housenum)

                            startActivity(intent)
                        }

                    })

                    stdRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}