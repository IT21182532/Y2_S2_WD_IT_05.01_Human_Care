package com.example.sample1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sample1.R
import com.example.sample1.adapters.ReqAdapter
import com.example.sample1.models.ExpenceModel
import com.google.firebase.database.*

class FetchingActivityExpn : AppCompatActivity() {

    private lateinit var empRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var empList: ArrayList<ExpenceModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetchingexpn)

        empRecyclerView = findViewById(R.id.rvEmp)
        empRecyclerView.layoutManager = LinearLayoutManager(this)
        empRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        empList = arrayListOf<ExpenceModel>()
        getEmployeesData()
    }
    private fun getEmployeesData(){
        empRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Requirements")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if(snapshot.exists()){
                    for(empSnap in snapshot.children){
                        val empData = empSnap.getValue(ExpenceModel::class.java)
                        empList.add(empData!!)
                    }
                    val mAdapter = ReqAdapter(empList)
                    empRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : ReqAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@FetchingActivityExpn, RequirementsDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("REQ1", empList[position].REQ1)
                            intent.putExtra("REQ2", empList[position].REQ2)
                            intent.putExtra("REQ3", empList[position].REQ3)
                            intent.putExtra("REQ4", empList[position].REQ4)
                            intent.putExtra("REQ5", empList[position].REQ5)
                            intent.putExtra("REQ6", empList[position].REQ6)
                            startActivity(intent)
                        }

                    })

                    empRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}