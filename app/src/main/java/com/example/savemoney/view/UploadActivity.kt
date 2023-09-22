package com.example.savemoney.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.savemoney.R
import com.example.savemoney.model.Goal
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import kotlinx.android.synthetic.main.activity_upload.*

class UploadActivity : AppCompatActivity() {


    private var mStorageRef:StorageReference? = null
    private var mDatabaseRef:DatabaseReference? = null
    private var mUploadTask: StorageTask<*>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        /**set data*/

        mStorageRef = FirebaseStorage.getInstance().getReference("goals_uploads")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("goals_uploads")
        backBtn.setOnClickListener{
            startActivity(Intent(this,ItemsActivity::class.java))
        }

        upLoadBtn.setOnClickListener {
            if (mUploadTask != null && mUploadTask!!.isInProgress){
                Toast.makeText(this@UploadActivity,
                    "An Upload is Still in Progress",
                    Toast.LENGTH_SHORT).show()
            }
            else{
                uploadFile()
            }
        }
    }
    private fun uploadFile() {

                    val upload = Goal(
                        name = goalName!!.text.toString().trim { it <= ' ' },
                        amount = goalAmount!!.text.toString().trim { it <= ' ' },
                        date =  goalEnd!!.text.toString().trim { it <= ' ' }
                    )
                    val uploadId = mDatabaseRef!!.push().key
                    mDatabaseRef!!.child((uploadId)!!).setValue(upload)
        Toast.makeText(this,"Adding gaol success", Toast.LENGTH_SHORT).show()
        goalName.setText("")
        goalAmount.setText("")
        goalEnd.setText("")

                }
}