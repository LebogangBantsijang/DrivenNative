package com.lebogang.driven

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class DrivenApplication:Application() {
    val localDatabase:DrivenDatabase by lazy { DrivenDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
    }
}