package com.example.a911notes.util

import android.view.View

fun View.toggleVisibility() {
    visibility = if (visibility == View.VISIBLE) {View.INVISIBLE} else { View.VISIBLE}

}

fun View.rotate90() {
    rotation = (rotation + 90) % 360

}