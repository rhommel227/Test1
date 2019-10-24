package com.example.a911notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import com.example.a911notes.CountRepository

class CountViewModel(application: Application) : AndroidViewModel(application) {    //sets up the View Model that can be used in the main activity to access and store live data
    private val repository = CountRepository(application.applicationContext)  //I do not entirely know what this value is or what it does

    fun getUserCount(name: String) = repository.getUserCount(name)  //creates the functions that will be used in the main activity from the repository and the value created above

    fun setUserCount(name: String, count: Long) = repository.setUserCount(name, count)
}
