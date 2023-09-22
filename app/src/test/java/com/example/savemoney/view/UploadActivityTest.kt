package com.example.savemoney.view

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UploadActivityTest {
    private lateinit var scenario: ActivityScenario<UploadActivity>
    @Before
    fun setUp(){

        scenario.moveToState(Lifecycle.State.STARTED)

    }
    @Test
    fun testAddingGoals(){

    }


}