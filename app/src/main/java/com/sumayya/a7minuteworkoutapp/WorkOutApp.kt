package com.sumayya.a7minuteworkoutapp

import android.app.Application

// Create the application class
class WorkOutApp: Application() {

    val db:HistoryDatabase by lazy {
        HistoryDatabase.getInstance(this)
    }
}