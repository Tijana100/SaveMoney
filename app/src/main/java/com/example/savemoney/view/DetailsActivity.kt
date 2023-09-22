package com.example.savemoney.view

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemoney.R
import com.example.savemoney.adapter.MoneyAdapter
import com.example.savemoney.model.Money
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private lateinit var moneyList:ArrayList<Money>
    private lateinit var moneyAdapter: MoneyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        moneyRV.setHasFixedSize(true)
        moneyRV.layoutManager = LinearLayoutManager(this@DetailsActivity)
        moneyList = ArrayList()
        moneyAdapter = MoneyAdapter(this@DetailsActivity,moneyList)
        moneyRV.adapter = moneyAdapter
        val money = findViewById<EditText>(R.id.mAmount)
        val intss = intent
        var nameT = intss.getStringExtra("NAMET")
        var amountT = intss.getStringExtra("AMOUNTT")
        var dateT = intss.getStringExtra("DATET")

        TitleTV.text = nameT
        AmountTV.text = amountT
        DateTV.text = dateT

        saveBtn.setOnClickListener{

           val moneyy = money.text.toString()
            moneyList.add(Money("Amount: $moneyy"))
            moneyAdapter.notifyDataSetChanged()
           money.setText("")
        }

}}