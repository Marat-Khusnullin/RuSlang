package com.application_ruslang.ruslang

import android.app.Application
import android.content.Context
import java.util.*

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null
        val OBJECT_COUNT = 30
        val loadingId = UUID.randomUUID()
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any

        // Use ApplicationContext.
        // example: SharedPreferences etc...
        //val context: Context = App.applicationContext()
    }

}


