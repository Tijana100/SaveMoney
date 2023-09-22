package com.example.savemoney.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.savemoney.R
import com.example.savemoney.model.Goal
import com.example.savemoney.model.Goals
import com.example.savemoney.view.ItemsActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_upload.*
import kotlinx.android.synthetic.main.goals_row.view.*

class GoalsAdapter(private val goals: List<Goals>) : RecyclerView.Adapter<GoalsAdapter.ViewHolder>() {


   inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
       private var mDatabaseRef: DatabaseReference? = null
        val name:TextView = itemView.name
        val amount:TextView = itemView.amount
        val date:TextView = itemView.date
        val send:ImageView
        init {
            send = itemView.mMenus
            send.setOnClickListener{ uploadFile()}
            mDatabaseRef= FirebaseDatabase.getInstance().getReference("goals_uploads")
        }


       private fun uploadFile() {

           val upload = Goal(
               name = name!!.text.toString().trim { it <= ' ' },
               amount = amount!!.text.toString().trim { it <= ' ' },
               date =  date!!.text.toString().trim { it <= ' ' }
           )
           val uploadId = mDatabaseRef!!.push().key
           mDatabaseRef!!.child((uploadId)!!).setValue(upload)



       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.goals_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goal = goals[position]
        holder.name.text = goal.title
        holder.amount.text = goal.amount
        holder.date.text = goal.time
    }

    override fun getItemCount() = goals.size

}
