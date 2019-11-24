package com.example.a911notes.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.example.a911notes.R
import com.example.a911notes.util.rotate90
import com.example.a911notes.viewmodel.CountViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var imageCounter: Long = 0      //creates variable to track clicks on the myButton button



    private lateinit var countViewModel: CountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {        //sets up what the application will do when created or started
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)        //I do not entirely know what specifically these two lines do, but I think it is setting an observer onto the userName so that it is stored as live data
        countViewModel.getUserCount( intent.extras?.get("username").toString()).observe(this,
            androidx.lifecycle.Observer {
                updateCounter(it)})




        if (imageCounter > 0) {     //sets the image counter to display a count if it is larger than zero
           updateCounter(imageCounter)
        }

        myButton.setOnClickListener {       //the main button, when clicked associates the counted number of clicks to the user name as live data
            countViewModel.setUserCount(intent.extras?.get("username").toString(), imageCounter + 1)
            myImage.rotate90()      //rotates image when clicked for visual feedback




            if (imageCounter < 10){ myEnableButton.setClickable(false)}  //makes the second button unclickable and invisible unless the click counter is at or over 10
            else if (imageCounter >= 10){
                myEnableButton.setClickable(true)
                myEnableButton.isVisible = true
            }

        }


        myEnableButton.setOnClickListener{      //when the second button is visable and clickable, when clicked it subtracts 10 from the click counter so it is like you are spending clicks to 'buy' a new button which becomes visable and clickable once this button is pressed
            countViewModel.setUserCount(intent.extras?.get("username").toString(), imageCounter - 10)
            button2.isVisible = true
            myEnableButton.isVisible = false

            myCounter.text = imageCounter.toString()
        }

        button2.setOnClickListener{ //a third button which when clicked adds 2 clicks instead of 1 to the click counter
            countViewModel.setUserCount(intent.extras?.get("username").toString(), imageCounter + 2)
            myImage.rotate90()
            myImage.rotate90()

            myCounter.text = imageCounter.toString()
        }

        searchActivityButton.setOnClickListener {
            startActivity((Intent(this, SearchActivity::class.java)))
        }

        DownloadmanagerActivityBtn.setOnClickListener {
            startActivity((Intent(this, DownloadmanagerActivity::class.java)))
        }


    }



    private fun updateCounter(count: Long){ // function that updates the click counter when called
        imageCounter = count
        myCounter.text = imageCounter.toString()
    }




}