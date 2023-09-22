package com.example.savemoney.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemoney.R
import com.example.savemoney.adapter.GoalsAdapter
import com.example.savemoney.model.Goals
import kotlinx.android.synthetic.main.activity_goals_api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GoalsApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals_api)
        seeBtn.setOnClickListener{
            startActivity(Intent(this,ItemsActivity::class.java))
        }


        val retrofit = Retrofit.Builder()
            .baseUrl("https://mocki.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiService::class.java)
        api.fetchAllGoals().enqueue(object : Callback<List<Goals>> {
            override fun onResponse(call: Call<List<Goals>>, response: Response<List<Goals>>) {
             showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<Goals>>, t: Throwable) {
                d("daniel","onFailure")
            }

        })
    }

    private fun showData(goals: List<Goals>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@GoalsApiActivity)
            adapter = GoalsAdapter(goals)
        }
    }

}