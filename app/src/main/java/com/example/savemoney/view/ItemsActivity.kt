package com.example.savemoney.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemoney.R
import com.example.savemoney.adapter.ListAdapter
import com.example.savemoney.model.Goal
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class ItemsActivity : AppCompatActivity() {
    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mGoals:ArrayList<Goal>
    private lateinit var listAdapter: ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@ItemsActivity)

        mGoals = ArrayList()
        listAdapter = ListAdapter(this@ItemsActivity, mGoals )
        mRecyclerView.adapter = listAdapter
        /**set Firebase Database*/
        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("goals_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ItemsActivity,error.message, Toast.LENGTH_SHORT).show()


            }

            override fun onDataChange(snapshot: DataSnapshot) {
                mGoals.clear()
                for (goalSnapshot in snapshot.children){
                    val upload = goalSnapshot.getValue(Goal::class.java)
                    upload!!.key = goalSnapshot.key
                    mGoals.add(upload)

                }
                listAdapter.notifyDataSetChanged()


            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }

}
