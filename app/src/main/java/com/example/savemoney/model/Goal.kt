package com.example.savemoney.model

import com.google.firebase.database.Exclude


data class Goal(
    var name:String?=null,
    var amount:String? = null,
    var date:String? = null,
    @get:Exclude
    @set:Exclude
    var key:String?=null

)