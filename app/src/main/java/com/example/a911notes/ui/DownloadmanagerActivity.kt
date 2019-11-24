package com.example.a911notes.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity


import kotlinx.android.synthetic.main.activity_downloadmanager.*
import android.app.DownloadManager
import android.content.IntentFilter
import android.R
import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import android.annotation.TargetApi
import android.content.Context
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import java.io.File


class DownloadmanagerActivity : AppCompatActivity() {

    private var button: Button? = null
    private var downloadID: Long = 0
    private val onDownloadComplete = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.a911notes.R.layout.activity_main)
        button = findViewById(com.example.a911notes.R.id.download)
        registerReceiver(onDownloadComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        button!!.setOnClickListener(object : View.OnClickListener {
            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(view: View) {
                beginDownload()
            }
        })
    }

    public override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(onDownloadComplete)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun beginDownload() {
        val file = File(getExternalFilesDir(null), "Dummy")

        val request =
            DownloadManager.Request(Uri.parse("http://speedtest.ftp.otenet.gr/files/test10Mb.db"))
                .setTitle("Dummy File")
                .setDescription("Downloading")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationUri(Uri.fromFile(file))
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)
        val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadID =
            downloadManager.enqueue(request)
    }


}
