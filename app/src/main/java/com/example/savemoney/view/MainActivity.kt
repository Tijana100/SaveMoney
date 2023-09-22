package com.example.savemoney.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.savemoney.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonClick = findViewById<Button>(R.id.apiBtn)
        apiBtn.setOnClickListener{
           val intent = Intent(this,GoalsApiActivity::class.java)
            startActivity(intent)
        }
        listBtn.setOnClickListener{
            startActivity(Intent(this,ItemsActivity::class.java))
        }
        fab.setOnClickListener{
            startActivity(Intent(this,UploadActivity::class.java))
        }

    }
}