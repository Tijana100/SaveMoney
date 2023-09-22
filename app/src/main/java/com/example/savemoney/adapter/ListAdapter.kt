package com.example.savemoney.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.savemoney.R
import com.example.savemoney.model.Goal
import com.example.savemoney.view.DetailsActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.row_item.view.*


class ListAdapter (var mContext:Context,var goalList:ArrayList<Goal>):
    RecyclerView.Adapter<ListAdapter.ListViewHolder>()
{


    inner class ListViewHolder(var v:View): RecyclerView.ViewHolder(v) {
        private var mDatabaseRef: DatabaseReference? = null

        var mGoal = v.findViewById<TextView>(R.id.mGoal)
        var mAmount = v.findViewById<TextView>(R.id.mAmount)
        var mDate = v.findViewById<TextView>(R.id.mEnds)

       val mDelete :ImageView = itemView.mDelete

        init {

            mDatabaseRef= FirebaseDatabase.getInstance().getReference("goals_uploads")
            mDelete.setOnClickListener(){
                AlertDialog.Builder(mContext)
                    .setTitle("Delete")
                    .setIcon(R.drawable.ic__warning)
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes"){
                            dialog,_->
                        goalList.removeAt(adapterPosition)
                        notifyDataSetChanged()
                        Toast.makeText(mContext,"Goal has been deleted", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                    .setNegativeButton("No"){
                            dialog,_->
                        dialog.dismiss()
                    }
                    .create()
                    .show()

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        var infalter = LayoutInflater.from(parent.context)
        var v = infalter.inflate(R.layout.row_item,parent,false)
        return ListViewHolder(v)
    }

    override fun getItemCount(): Int =goalList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var newList = goalList[position]
        holder.mGoal.text = newList.name
        holder.mAmount.text = newList.amount
        holder.mDate.text = newList.date
        holder.v.setOnClickListener {

            val name = newList.name
            val amount = newList.amount
            val date = newList.date

            val mIntent = Intent(mContext, DetailsActivity::class.java)
            mIntent.putExtra("NAMET",name)
            mIntent.putExtra("AMOUNTT",amount)
            mIntent.putExtra("DATET",date)
            mContext.startActivity(mIntent)
        }
    }

    }




