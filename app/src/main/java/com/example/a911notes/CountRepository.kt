package com.example.a911notes

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import me.ibrahimsn.library.LiveSharedPreferences

class CountRepository(context: Context) {       //this is the repository class using plugins from the ibrahimsn library accessed in the gradle
    private val preferences: SharedPreferences
    private val liveSharedPreferences : LiveSharedPreferences

    init {      //sets up the live data that will be accessed or stored
        preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        liveSharedPreferences = LiveSharedPreferences(preferences)
    }

    fun setUserCount(name: String, count: Long) {       //creates function to be used to set username and the number associated with it
        preferences.edit().putLong(name, count).apply()
    }

    fun getUserCount(name: String): LiveData<Long> =    //accesses the userCount function as livedata
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name), 0L)) { it[name] }
    companion object {
        private const val PREFS = "clickCounts"
    }
}