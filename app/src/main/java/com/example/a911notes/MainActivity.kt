package com.example.a911notes

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a911notes.util.rotate90
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private var imageCounter: Long = 0
    private var userName: String = ""
    private fun getStore() = getPreferences(Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userName = intent.extras?.get("username").toString()

        if (savedInstanceState != null) {
           imageCounter = savedInstanceState.getLong(IMAGE_COUNTER_KEY, 0)
        } else if (getStore().contains(userName)) {
            imageCounter = getStore().getLong(userName, 0)
        }

        if (imageCounter > 0) {
            myCounter.text = imageCounter.toString()
        }

        myButton.setOnClickListener {
            imageCounter++
            myImage.rotate90()

            myCounter.text = imageCounter.toString()
        }
    }



    override fun onPause() {
        super.onPause()
        getStore().edit().putLong(userName, imageCounter).apply()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putLong(IMAGE_COUNTER_KEY, imageCounter)
        }

        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val IMAGE_COUNTER_KEY = "imageCounterKey"
    }
}