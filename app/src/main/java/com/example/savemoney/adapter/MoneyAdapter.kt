package com.example.savemoney.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.savemoney.R
import com.example.savemoney.model.Money
import kotlinx.android.synthetic.main.money_row.view.*

class MoneyAdapter (var mContext: Context, var moneyList:ArrayList<Money>):
    RecyclerView.Adapter<MoneyAdapter.ListViewHolder>() {
    inner class ListViewHolder(v: View):RecyclerView.ViewHolder(v){
        val mMoney: TextView = v.findViewById(R.id.mmAmount)
        private val mmDelete : ImageView = itemView.mmDelete

        init {

            mmDelete.setOnClickListener{
                AlertDialog.Builder(mContext)
                    .setTitle("Delete")
                    .setIcon(R.drawable.ic__warning)
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes"){
                            dialog,_->
                        moneyList.removeAt(adapterPosition)
                        notifyDataSetChanged()
                        Toast.makeText(mContext,"Amount has been deleted", Toast.LENGTH_SHORT).show()
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyAdapter.ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.money_row,parent,false)
        return ListViewHolder(v)
    }
    override fun getItemCount(): Int =moneyList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val newList = moneyList[position]
        holder.mMoney.text = newList.moneyAmount

    }

}