package com.example.a911notes.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.example.a911notes.R
import com.example.a911notes.util.rotate90
import com.example.a911notes.viewmodel.CountViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var imageCounter: Long = 0
    private var userName: String = ""


    private lateinit var countViewModel: CountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)
        countViewModel.getUserCount( intent.extras?.get("username").toString()).observe(this,
            androidx.lifecycle.Observer {
                updateCounter(it)})

        userName = intent.extras?.get("username").toString()


        if (imageCounter > 0) {
           updateCounter(imageCounter)
        }

        myButton.setOnClickListener {
            countViewModel.setUserCount(intent.extras?.get("username").toString(), imageCounter + 1)
            myImage.rotate90()




            if (imageCounter < 10){ myEnableButton.setClickable(false)}
            else if (imageCounter >= 10){
                myEnableButton.setClickable(true)
                myEnableButton.isVisible = true
            }

        }


        myEnableButton.setOnClickListener{
            countViewModel.setUserCount(intent.extras?.get("username").toString(), imageCounter - 10)
            button2.isVisible = true
            myEnableButton.isVisible = false

            myCounter.text = imageCounter.toString()
        }

        button2.setOnClickListener{
            countViewModel.setUserCount(intent.extras?.get("username").toString(), imageCounter + 2)
            myImage.rotate90()
            myImage.rotate90()

            myCounter.text = imageCounter.toString()
        }


    }


    private fun updateCounter(count: Long){
        imageCounter = count
        myCounter.text = imageCounter.toString()
    }




}